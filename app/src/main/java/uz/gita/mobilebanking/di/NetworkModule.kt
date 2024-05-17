package uz.gita.mobilebanking.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.mobilebanking.data.source.local.SharedPreferenceHelper
import uz.gita.mobilebanking.data.source.remote.TokenAuthenticator
import uz.gita.mobilebanking.data.source.remote.api.CardApi
import uz.gita.mobilebanking.data.source.remote.api.RegistrationApi
import uz.gita.mobilebanking.data.source.remote.api.TransferApi
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @[Provides Singleton]
    fun provideGson(): Gson = Gson()

    @[Provides Singleton]
    fun provideOkHttp(
        @ApplicationContext context: Context,
        sharedPreferenceHelper: SharedPreferenceHelper,
        cardApi: Provider<CardApi>
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(ChuckerInterceptor(context = context))
            .addInterceptor(TokenAuthenticator(sharedPreferenceHelper, cardApi))
            .build()
    }

    @[Provides Singleton]
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://195.158.16.140/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @[Provides Singleton]
    fun provideRegistrationApi(retrofit: Retrofit): RegistrationApi =
        retrofit.create(RegistrationApi::class.java)

    @[Provides Singleton]
    fun provideCardApi(retrofit: Retrofit): CardApi =
        retrofit.create(CardApi::class.java)

    @[Provides Singleton]
    fun provideTransferApi(retrofit: Retrofit): TransferApi =
        retrofit.create(TransferApi::class.java)
}