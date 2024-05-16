package uz.gita.mobilebanking.data.source.remote.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import uz.gita.mobilebanking.data.model.request.CardAddRequest
import uz.gita.mobilebanking.data.model.request.UpdateTokenRequest
import uz.gita.mobilebanking.data.model.response.CardAddResponse
import uz.gita.mobilebanking.data.model.response.UpdateTokenResponse

interface CardApi {
    @POST("mobile-bank/v1/card")
    suspend fun addCard(@Body data: CardAddRequest): Response<CardAddResponse>

    @GET("mobile-bank/v1/card")
    suspend fun getCards(): Response<List<CardAddResponse>>

    @POST("mobile-bank/v1/auth/update-token")
    fun refreshToken(@Body data: UpdateTokenRequest): Call<UpdateTokenResponse>
}