package uz.gita.mobilebanking.data.model.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardData(
    val id: String,
    val pan: String,
    val expiredYear: String,
    val expiredMonth: String,
    val name: String,
    val money: String,
) : Parcelable
