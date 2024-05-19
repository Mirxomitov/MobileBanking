package uz.gita.mobilebanking.data.model.response.transfer

import com.google.gson.annotations.SerializedName

data class TransferHistoryResponse(
    val child: List<HistoryChildResponse>,
    @SerializedName("current-page")
    val currentPage: Int,
    @SerializedName("total-elements")
    val totalElements: Int,
    @SerializedName("total-pages")
    val totalPages: Int
)