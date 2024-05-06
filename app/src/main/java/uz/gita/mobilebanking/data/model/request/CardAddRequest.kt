package uz.gita.mobilebanking.data.model.request

import com.google.gson.annotations.SerializedName

data class CardAddRequest(
    @SerializedName("pan")
    val pan: String,
    @SerializedName("expired-year")
    val expiredYear: String,
    @SerializedName("expired-month")
    val expiredMonth: String,
    @SerializedName("name")
    val name: String,
)
