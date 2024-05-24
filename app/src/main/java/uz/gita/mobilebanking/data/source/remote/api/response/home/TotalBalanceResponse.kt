package uz.gita.mobilebanking.data.source.remote.api.response.home

import com.google.gson.annotations.SerializedName

data class TotalBalanceResponse (
    @SerializedName("total-balance")
    val totalBalance :  Int,
)