package uz.gita.mobilebanking.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.gita.mobilebanking.data.source.local.entity.PayedCardEntity

@Dao
interface PayedCardsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: PayedCardEntity)

    @Delete
    fun delete(data: PayedCardEntity)

    @Query("SELECT * FROM payed_cards_table")
    fun all(): List<PayedCardEntity>

    @Query("SELECT * FROM payed_cards_table WHERE pan =:pan")
    fun getCardByPan(pan: String): PayedCardEntity
}