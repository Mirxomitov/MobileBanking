package uz.gita.mobilebanking.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.gita.mobilebanking.data.source.local.entity.TemplateCardEntity


@Dao
interface TemplateCardsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: TemplateCardEntity)

    @Delete
    fun delete(data: TemplateCardEntity)

    @Query("SELECT * FROM template_cards_table")
    fun all(): List<TemplateCardEntity>

    @Query("SELECT * FROM template_cards_table WHERE pan =:pan")
    fun getCardByPan(pan: String): TemplateCardEntity
}