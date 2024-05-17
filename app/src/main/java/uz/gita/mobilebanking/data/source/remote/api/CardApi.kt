package uz.gita.mobilebanking.data.source.remote.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import uz.gita.mobilebanking.data.model.request.card.CardAddRequest
import uz.gita.mobilebanking.data.model.request.UpdateTokenRequest
import uz.gita.mobilebanking.data.model.response.card.CardAddResponse
import uz.gita.mobilebanking.data.model.response.card.CardDeleteResponse
import uz.gita.mobilebanking.data.model.response.card.CardGetResponse
import uz.gita.mobilebanking.data.model.response.UpdateTokenResponse

interface CardApi {
    @POST("mobile-bank/v1/card")
    suspend fun addCard(@Body data: CardAddRequest): Response<CardAddResponse>
    @GET("mobile-bank/v1/card")
    suspend fun getCards(): Response<List<CardGetResponse>>
    @DELETE("mobile-bank/v1/card/{number}")
    suspend fun deleteCard(@Path("number") cardNumber: String): Response<CardDeleteResponse>
    @POST("mobile-bank/v1/auth/update-token")
    fun refreshToken(@Body data: UpdateTokenRequest): Call<UpdateTokenResponse>
}