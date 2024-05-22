package uz.gita.mobilebanking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebanking.domain.CardRepository
import uz.gita.mobilebanking.domain.HomeRepository
import uz.gita.mobilebanking.domain.RegistrationRepository
import uz.gita.mobilebanking.domain.TransferRepository
import uz.gita.mobilebanking.domain.impl.CardRepositoryImpl
import uz.gita.mobilebanking.domain.impl.HomeRepositoryImpl
import uz.gita.mobilebanking.domain.impl.RegistrationRepositoryImpl
import uz.gita.mobilebanking.domain.impl.TransferRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun registrationRepository(impl: RegistrationRepositoryImpl): RegistrationRepository

    @Binds
    fun cardRepository(impl: CardRepositoryImpl): CardRepository

    @Binds
    fun bindTransferRepository(impl: TransferRepositoryImpl): TransferRepository

    @Binds
    fun bindHomeRepository(impl : HomeRepositoryImpl) : HomeRepository
}