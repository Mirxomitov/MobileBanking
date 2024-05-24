package uz.gita.mobilebanking.data.source.remote.api.response.transfer

import com.google.gson.annotations.SerializedName

data class TransferHistoryResponse(
    @SerializedName("child")
    val child: List<uz.gita.mobilebanking.data.source.remote.api.response.transfer.HistoryChildResponse>,
    @SerializedName("current-page")
    val currentPage: Int,
    @SerializedName("total-elements")
    val totalElements: Int,
    @SerializedName("total-pages")
    val totalPages: Int
)