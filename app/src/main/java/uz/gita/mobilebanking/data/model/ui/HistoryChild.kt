package uz.gita.mobilebanking.data.model.ui

data class HistoryChild(
    val amount: Int,
    val from: String,
    val time: Long,
    val to: String,
    val type: String
)
