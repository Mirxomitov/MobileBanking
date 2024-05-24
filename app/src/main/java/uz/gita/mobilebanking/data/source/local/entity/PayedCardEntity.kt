package uz.gita.mobilebanking.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("payed_cards_table")
data class PayedCardEntity(
    @PrimaryKey
    val pan: String, // length == 16
    val ownerName: String,
)
