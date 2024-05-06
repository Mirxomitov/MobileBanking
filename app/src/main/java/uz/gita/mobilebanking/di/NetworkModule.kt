package uz.gita.mobilebanking.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.mobilebanking.data.source.remote.CardApi
import uz.gita.mobilebanking.data.source.remote.RegistrationApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @[Provides Singleton]
    fun provideGson(): Gson = Gson()

    @[Provides Singleton]
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient
            .Builder()
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
    fun provedCardApi(retrofit: Retrofit): CardApi =
        retrofit.create(CardApi::class.java)
}