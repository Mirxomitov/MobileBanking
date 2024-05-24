package uz.gita.mobilebanking.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.gita.mobilebanking.data.source.local.entity.TemplateEntity

@Dao
interface TemplatesDao {
    @Query("SELECT * FROM template_table")
    fun getAllTemplates(): List<TemplateEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTemplate(data: TemplateEntity)

    @Delete()
    fun deleteTemplate(data: TemplateEntity)
}