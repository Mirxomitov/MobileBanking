package uz.gita.mobilebanking.data.model

data class PayedCardData(
    val pan: String, // length == 16
    val ownerName: String,
)
