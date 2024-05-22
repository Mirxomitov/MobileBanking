package uz.gita.mobilebanking.data.model.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransferHistoryChildData(
    val amount: Int,
    val from: String,
    val time: Long,
    val to: String,
    val type: String
) : Parcelable
