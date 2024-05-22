package uz.gita.mobilebanking.data.model.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransferHistoryData(
    val child: List<TransferHistoryChildData>,
    val currentPage: Int,
    val totalElements: Int,
    val totalPages: Int
) : Parcelable
