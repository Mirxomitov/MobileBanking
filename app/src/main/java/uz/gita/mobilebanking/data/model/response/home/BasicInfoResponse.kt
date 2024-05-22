package uz.gita.mobilebanking.data.model.response.home

import com.google.gson.annotations.SerializedName

data class BasicInfoResponse(
    @SerializedName("firsrt-name")
    val firstName: String,
    @SerializedName("gender-type")
    val genderType: Int,
    @SerializedName("age")
    val age: Int,
)
