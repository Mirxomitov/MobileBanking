package uz.gita.mobilebanking.data.model.ui

data class CardData(
    val id : String,
    val pan: String,
    val expiredYear: String,
    val expiredMonth: String,
    val name: String,
    val money : String,
)
