package uz.gita.mobilebanking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebanking.domain.repositories.CardRepository
import uz.gita.mobilebanking.domain.repositories.HomeRepository
import uz.gita.mobilebanking.domain.repositories.RegistrationRepository
import uz.gita.mobilebanking.domain.repositories.TransferRepository
import uz.gita.mobilebanking.domain.repositories.impl.CardRepositoryImpl
import uz.gita.mobilebanking.domain.repositories.impl.HomeRepositoryImpl
import uz.gita.mobilebanking.domain.repositories.impl.RegistrationRepositoryImpl
import uz.gita.mobilebanking.domain.repositories.impl.TransferRepositoryImpl

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