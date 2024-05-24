package uz.gita.mobilebanking.data.source.remote.api.request.card

import com.google.gson.annotations.SerializedName

data class UpdateTokenRequest(
    @SerializedName("refresh-token")
    val refreshToken: String,
)
