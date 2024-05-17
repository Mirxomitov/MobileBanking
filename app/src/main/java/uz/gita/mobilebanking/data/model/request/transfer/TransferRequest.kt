package uz.gita.mobilebanking.data.model.request.transfer

import com.google.gson.annotations.SerializedName

data class TransferRequest(
    @SerializedName("type")
    val type: String = "third-card",
    @SerializedName("sender-id")
    val senderId: String,
    @SerializedName("receiver-pan")
    val receiverPan: String,
    @SerializedName("amount")
    val amount: Int,
)