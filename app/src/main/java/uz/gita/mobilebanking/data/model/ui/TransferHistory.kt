package uz.gita.mobilebanking.data.model.ui

data class TransferHistory(
    val child: List<HistoryChild>,
    val currentPage: Int,
    val totalElements: Int,
    val totalPages: Int
)
