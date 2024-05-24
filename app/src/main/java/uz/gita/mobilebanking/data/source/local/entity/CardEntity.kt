package uz.gita.mobilebanking.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("card_table")
data class CardEntity(
    @PrimaryKey val id: String,
    val name: String,
    val amount: Long,
    val owner: String,
    val pan: String,
    val expireYear: Int,
    val expiredMonth: Int,
    val themeType: Int,
    val isVisible: Boolean
)