package uz.gita.mobilebanking.data.source.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.mobilebanking.data.model.request.transfer.TransferRequest
import uz.gita.mobilebanking.data.model.request.transfer.TransferVerifyRequest
import uz.gita.mobilebanking.data.model.response.transfer.TransferResponse
import uz.gita.mobilebanking.data.model.response.transfer.TransferVerifyResponse

interface TransferApi {
    @POST("mobile-bank/v1/transfer/transfer")
    suspend fun transfer(@Body data: TransferRequest): Response<TransferResponse>
    @POST("mobile-bank/v1/transfer/transfer/verify")
    suspend fun transferVerify(@Body data: TransferVerifyRequest): Response<TransferVerifyResponse>
}