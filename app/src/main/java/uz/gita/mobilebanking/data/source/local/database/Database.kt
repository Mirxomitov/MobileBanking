package uz.gita.mobilebanking.data.source.local.database

import androidx.room.RoomDatabase
import uz.gita.mobilebanking.data.source.local.dao.CardsDao
import uz.gita.mobilebanking.data.source.local.dao.PayedCardsDao
import uz.gita.mobilebanking.data.source.local.dao.TemplateCardsDao
import uz.gita.mobilebanking.data.source.local.dao.TemplatesDao
import uz.gita.mobilebanking.data.source.local.entity.CardEntity
import uz.gita.mobilebanking.data.source.local.entity.TemplateCardEntity
import uz.gita.mobilebanking.data.source.local.entity.PayedCardEntity
import uz.gita.mobilebanking.data.source.local.entity.TemplateEntity
import javax.inject.Singleton

@Singleton
@androidx.room.Database(entities = [CardEntity::class, TemplateEntity::class, PayedCardEntity::class, TemplateCardEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun getCardsDao(): CardsDao
    abstract fun getTemplateDao(): TemplatesDao
    abstract fun getPayedCardsDao(): PayedCardsDao
    abstract fun getTemplateCardsDao() : TemplateCardsDao
}