package uz.gita.mobilebanking.data.source.remote.api.request.card

import com.google.gson.annotations.SerializedName

data class CardUpdateRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("theme-type")
    val themeType: Int,
    @SerializedName("is-visible")
    val isVisible: Boolean,
)
/*
{
    "id": 3,
    "name": "Basic",
    "theme-type": 3,
    "is-visible": "false"
}
 */
