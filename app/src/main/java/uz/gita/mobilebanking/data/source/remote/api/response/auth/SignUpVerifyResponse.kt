package uz.gita.mobilebanking.data.source.remote.api.response.auth

import com.google.gson.annotations.SerializedName

data class SignUpVerifyResponse(
    @SerializedName("refresh-token")
    val refreshToken: String,

    @SerializedName("access-token")
    val accessToken: String,
)