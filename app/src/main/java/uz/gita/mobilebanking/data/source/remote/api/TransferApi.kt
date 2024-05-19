package uz.gita.mobilebanking.data.source.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.mobilebanking.data.model.request.transfer.CardOwnerByPanRequest
import uz.gita.mobilebanking.data.model.request.transfer.TransferRequest
import uz.gita.mobilebanking.data.model.request.transfer.TransferResendRequest
import uz.gita.mobilebanking.data.model.request.transfer.TransferVerifyRequest
import uz.gita.mobilebanking.data.model.response.transfer.CardOwnerByPanResponse
import uz.gita.mobilebanking.data.model.response.transfer.TransferHistoryResponse
import uz.gita.mobilebanking.data.model.response.transfer.TransferResendResponse
import uz.gita.mobilebanking.data.model.response.transfer.TransferResponse
import uz.gita.mobilebanking.data.model.response.transfer.TransferVerifyResponse

interface TransferApi {
    @POST("mobile-bank/v1/transfer/transfer")
    suspend fun transfer(@Body data: TransferRequest): Response<TransferResponse>

    @POST("mobile-bank/v1/transfer/transfer/verify")
    suspend fun transferVerify(@Body data: TransferVerifyRequest): Response<TransferVerifyResponse>

    @POST("mobile-bank/v1/transfer/card-owner")
    suspend fun getCardOwnerByPan(@Body pan: CardOwnerByPanRequest): Response<CardOwnerByPanResponse>

    @POST("mobile-bank/v1/transfer/transfer/resend")
    suspend fun transferResend(@Body data: TransferResendRequest): Response<TransferResendResponse>

    @POST("mobile-bank/v1/transfer/history") //?size={size}&current-page={page}
    suspend fun getHistory(): Response<TransferHistoryResponse>
}