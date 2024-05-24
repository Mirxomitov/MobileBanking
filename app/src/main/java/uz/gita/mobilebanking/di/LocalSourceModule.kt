package uz.gita.mobilebanking.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebanking.data.source.local.dao.CardsDao
import uz.gita.mobilebanking.data.source.local.dao.PayedCardsDao
import uz.gita.mobilebanking.data.source.local.dao.TemplateCardsDao
import uz.gita.mobilebanking.data.source.local.dao.TemplatesDao
import uz.gita.mobilebanking.data.source.local.database.Database
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalSourceModule {

    @[Provides Singleton]
    fun provideAppDatabase(@ApplicationContext context: Context): Database = Room
        .databaseBuilder(context, Database::class.java, "cards.db")
        .build()

    @[Provides Singleton]
    fun provideCardDao(database: Database): CardsDao = database.getCardsDao()

    @[Provides Singleton]
    fun providePayedCardsDao(database: Database): PayedCardsDao = database.getPayedCardsDao()

    @[Provides Singleton]
    fun provideTemplatesDao(database: Database): TemplatesDao = database.getTemplateDao()

    @[Provides Singleton]
    fun provideTemplateCardsDao(database: Database): TemplateCardsDao = database.getTemplateCardsDao()
}