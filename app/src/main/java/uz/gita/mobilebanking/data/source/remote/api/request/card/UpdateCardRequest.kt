package uz.gita.mobilebanking.data.source.remote.api.request.card

import com.google.gson.annotations.SerializedName

data class UpdateCardRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String = "Personal",
    @SerializedName("theme-type")
    val themeType: Int,
    @SerializedName("is-visible")
    val isVisible: Boolean
)
