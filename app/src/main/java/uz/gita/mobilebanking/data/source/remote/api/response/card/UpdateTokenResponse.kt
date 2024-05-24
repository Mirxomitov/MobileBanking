package uz.gita.mobilebanking.data.source.remote.api.response.card

import com.google.gson.annotations.SerializedName

data class UpdateTokenResponse(
    @SerializedName("refresh-token")
    val refreshToken: String,
    @SerializedName("access-token")
    val accessToken: String,
)
