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
/*
{
    "pan": "1234567898765432",
    "expired-year": "2023",
    "expired-month": "1",
    "name": "Personal"
}
 */