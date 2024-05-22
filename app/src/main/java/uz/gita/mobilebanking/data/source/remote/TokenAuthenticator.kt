package uz.gita.mobilebanking.data.source.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import uz.gita.mobilebanking.data.model.request.card.UpdateTokenRequest
import uz.gita.mobilebanking.data.source.local.SharedPreferenceHelper
import uz.gita.mobilebanking.data.source.remote.api.CardApi
import javax.inject.Provider

class TokenAuthenticator(
    private val pref: SharedPreferenceHelper,
    private val cardApi: Provider<CardApi>
) : Interceptor {
    private fun refreshToken(chain: Interceptor.Chain, request: Request): Response {
        val response = cardApi.get().refreshToken(UpdateTokenRequest(pref.refreshToken())).execute()
        if (response.isSuccessful) {
            val access = response.body()?.accessToken
            val refresh = response.body()?.refreshToken
            pref.refreshToken(refresh.toString())
            pref.accessToken(access.toString())

            val newRequest = request.newBuilder()
                .removeHeader("Authorization")
                .addHeader("Authorization", "Bearer $access")
                .build()
            return chain.proceed(newRequest)
        }
        return chain.proceed(request)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = pref.accessToken()
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        val response = chain.proceed(request)

        if (response.code == 401) {
            response.close()
            return refreshToken(chain, request)
        }
        return response
    }

}