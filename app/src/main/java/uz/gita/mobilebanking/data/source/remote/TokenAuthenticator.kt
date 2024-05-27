package uz.gita.mobilebanking.data.source.remote

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import uz.gita.mobilebanking.data.source.local.pref.SharedPreferenceHelper
import uz.gita.mobilebanking.data.source.remote.api.CardApi
import uz.gita.mobilebanking.data.source.remote.api.request.card.UpdateTokenRequest
import uz.gita.mobilebanking.utils.network_status.NetworkStatus
import javax.inject.Provider

class TokenAuthenticator(
    private val pref: SharedPreferenceHelper,
    private val cardApi: Provider<CardApi>,
) : Interceptor {
    private fun refreshToken(chain: Interceptor.Chain, request: Request): Response {
        val hasNetwork = NetworkStatus.hasNetwork.value

        when {
            hasNetwork -> {
                val response = cardApi
                    .get()
                    .refreshToken(UpdateTokenRequest(pref.refreshToken()))
                    .execute()

                if (response.isSuccessful && response.body() != null) {
                    val access = response.body()!!.accessToken
                    val refresh = response.body()!!.refreshToken
                    pref.refreshToken(refresh)
                    pref.accessToken(access)

                    val newRequest = request.newBuilder()
                        .removeHeader("Authorization")
                        .addHeader("Authorization", "Bearer $access")
                        .build()

                    return chain.proceed(newRequest)
                }

                return createErrorResponse(request, "No network connection")
            }

            else -> {
                return createErrorResponse(request, "No network connection")
            }
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = pref.accessToken()
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            return createErrorResponse(request, "No network connection")
        }

        if (response.code == 401) {
            response.close()
            return refreshToken(chain, request)
        }

        return response
    }

    private fun createErrorResponse(request: Request, message: String): Response {
        val jsonMessage = """{"message": "$message"}""" // JSON object containing the message
        return Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .code(503) // Service Unavailable
            .message("Service Unavailable")
            .body(ResponseBody.create("application/json".toMediaTypeOrNull(), jsonMessage))
            .build()
    }
}