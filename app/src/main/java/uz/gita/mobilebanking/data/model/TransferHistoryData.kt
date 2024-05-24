package uz.gita.mobilebanking.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransferHistoryData(
    val child: List<HistoryChildData>,
    val currentPage: Int,
    val totalElements: Int,
    val totalPages: Int
) : Parcelable
