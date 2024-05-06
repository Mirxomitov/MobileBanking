package uz.gita.mobilebanking.data.source.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.mobilebanking.data.model.request.CardAddRequest
import uz.gita.mobilebanking.data.model.response.CardAddResponse

interface CardApi {
    @POST("mobile-bank/v1/card")
    suspend fun addCard(@Body data: CardAddRequest): Response<CardAddResponse>

    // delete mobile-bank/v1/card/3
}