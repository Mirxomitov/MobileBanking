package uz.gita.mobilebanking.data.source.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import uz.gita.mobilebanking.data.source.remote.api.request.transfer.CardOwnerByPanRequest
import uz.gita.mobilebanking.data.source.remote.api.request.transfer.TransferRequest
import uz.gita.mobilebanking.data.source.remote.api.request.transfer.TransferResendRequest
import uz.gita.mobilebanking.data.source.remote.api.request.transfer.TransferVerifyRequest
import uz.gita.mobilebanking.data.source.remote.api.response.base.BaseChildResponse
import uz.gita.mobilebanking.data.source.remote.api.response.transfer.CardOwnerByPanResponse
import uz.gita.mobilebanking.data.source.remote.api.response.transfer.TransferHistoryResponse
import uz.gita.mobilebanking.data.source.remote.api.response.transfer.TransferResendResponse
import uz.gita.mobilebanking.data.source.remote.api.response.transfer.TransferResponse
import uz.gita.mobilebanking.data.source.remote.api.response.transfer.TransferVerifyResponse

interface TransferApi {
    @POST("mobile-bank/v1/transfer/transfer")
    suspend fun transfer(@Body data: TransferRequest): Response<uz.gita.mobilebanking.data.source.remote.api.response.transfer.TransferResponse>

    @POST("mobile-bank/v1/transfer/transfer/verify")
    suspend fun transferVerify(@Body data: TransferVerifyRequest): Response<uz.gita.mobilebanking.data.source.remote.api.response.transfer.TransferVerifyResponse>

    @POST("mobile-bank/v1/transfer/card-owner")
    suspend fun getCardOwnerByPan(@Body pan: CardOwnerByPanRequest): Response<uz.gita.mobilebanking.data.source.remote.api.response.transfer.CardOwnerByPanResponse>

    @POST("mobile-bank/v1/transfer/transfer/resend")
    suspend fun transferResend(@Body data: TransferResendRequest): Response<uz.gita.mobilebanking.data.source.remote.api.response.transfer.TransferResendResponse>

    @GET("mobile-bank/v1/transfer/history")
    suspend fun getHistory(@Query("size") size: Int, @Query("current-page") currentPage: Int)
            : Response<uz.gita.mobilebanking.data.source.remote.api.response.transfer.TransferHistoryResponse>
}