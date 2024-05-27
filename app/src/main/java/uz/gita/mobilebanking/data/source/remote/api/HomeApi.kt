package uz.gita.mobilebanking.data.source.remote.api

import retrofit2.Response
import retrofit2.http.GET
import uz.gita.mobilebanking.data.source.remote.api.response.home.BasicInfoResponse
import uz.gita.mobilebanking.data.source.remote.api.response.home.FullInfoResponse
import uz.gita.mobilebanking.data.source.remote.api.response.home.TotalBalanceResponse

interface HomeApi {
    @GET("mobile-bank/v1/home/total-balance")
    suspend fun getTotalBalance(): Response<TotalBalanceResponse>
    @GET("mobile-bank/v1/home/user-info")
    suspend fun getBasicInfo(): Response<BasicInfoResponse>
    @GET("mobile-bank/v1/home/user-info/details")
    suspend fun getFullInfo(): Response<FullInfoResponse>

}