package uz.gita.mobilebanking.data.source.remote.api.response.home

import com.google.gson.annotations.SerializedName

data class FullInfoResponse(
    @SerializedName("born-date")
    val bornDate: Long,
    @SerializedName("first-name")
    val firstName: String,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("last-name")
    val lastName: String,
    @SerializedName("phone")
    val phone: String
)