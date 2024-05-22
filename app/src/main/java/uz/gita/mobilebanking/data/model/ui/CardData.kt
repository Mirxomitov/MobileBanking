package uz.gita.mobilebanking.data.model.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardData(
    val id: Int = 0,
    val name: String = "Default Name",
    val amount: Long = 0,
    val owner: String = "Default Owner",
    val pan: String = "0008000800080050",
    val expiredYear: Int = 0,
    val expiredMonth: Int = 0,
    val themeType: Int = 0,
    val isVisible: Boolean = true
) : Parcelable









