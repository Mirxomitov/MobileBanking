package uz.gita.mobilebanking.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.gita.mobilebanking.data.source.local.entity.CardEntity

@Dao
interface CardsDao {
    @Query("SELECT * FROM card_table")
    fun getAllCards(): List<CardEntity>

    @Insert
    fun insertAll(vararg cards: CardEntity)

    @Query("SELECT * FROM card_table WHERE pan =:pan")
    fun getCardByPan(pan: String): CardEntity

    @Query("DELETE  FROM card_table")
    fun deleteAllCards()
}
