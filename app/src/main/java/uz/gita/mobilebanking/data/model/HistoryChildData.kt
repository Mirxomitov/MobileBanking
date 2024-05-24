package uz.gita.mobilebanking.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryChildData(
    val amount: Int,
    val from: String,
    val time: Long,
    val to: String,
    val type: String
) : Parcelable
