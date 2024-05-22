package uz.gita.mobilebanking.data.model.request.card

import com.google.gson.annotations.SerializedName

data class UpdateTokenRequest(
    @SerializedName("refresh-token")
    val refreshToken: String,
)
