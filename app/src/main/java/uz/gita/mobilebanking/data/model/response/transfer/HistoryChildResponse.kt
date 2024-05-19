package uz.gita.mobilebanking.data.model.response.transfer

data class HistoryChildResponse(
    val amount: Int,
    val from: String,
    val time: Long,
    val to: String,
    val type: String
)