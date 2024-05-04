package uz.gita.mobilebanking.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferenceModule {

    @[Provides Singleton]
    fun bindSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("mobilebanking", Context.MODE_PRIVATE)
}
