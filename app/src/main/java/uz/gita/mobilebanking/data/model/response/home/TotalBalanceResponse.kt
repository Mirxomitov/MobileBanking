package uz.gita.mobilebanking.data.model.response.home

import com.google.gson.annotations.SerializedName

data class TotalBalanceResponse (
    @SerializedName("total-balance")
    val totalBalance :  Int,
)