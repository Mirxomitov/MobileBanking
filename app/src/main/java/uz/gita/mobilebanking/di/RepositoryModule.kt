package uz.gita.mobilebanking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebanking.domain.RegistrationRepository
import uz.gita.mobilebanking.domain.impl.RegistrationRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun registrationRepository(impl: RegistrationRepositoryImpl): RegistrationRepository
}