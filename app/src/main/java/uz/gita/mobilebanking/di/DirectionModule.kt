package uz.gita.mobilebanking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebanking.presentation.auth.AuthDirection
import uz.gita.mobilebanking.presentation.auth.AuthDirectionImpl
import uz.gita.mobilebanking.presentation.payments.PaymentsDirection
import uz.gita.mobilebanking.presentation.payments.PaymentsDirectionImpl
import uz.gita.mobilebanking.presentation.pin_check.PinCheckDirection
import uz.gita.mobilebanking.presentation.pin_check.PinCheckDirectionImpl
import uz.gita.mobilebanking.presentation.pin_create.CreatePinDirection
import uz.gita.mobilebanking.presentation.pin_create.CreatePinDirectionImpl
import uz.gita.mobilebanking.presentation.profile.ProfileDirection
import uz.gita.mobilebanking.presentation.profile.ProfileDirectionImpl
import uz.gita.mobilebanking.presentation.splash.SplashDirection
import uz.gita.mobilebanking.presentation.splash.SplashDirectionImpl
import uz.gita.mobilebanking.presentation.transactions.TransferDirection
import uz.gita.mobilebanking.presentation.transactions.TransferDirectionImpl
import uz.gita.mobilebanking.presentation.verify.VerifyDirection
import uz.gita.mobilebanking.presentation.verify.VerifyDirectionImpl


@Module
@InstallIn(SingletonComponent::class)
interface DirectionModule {
    @Binds
    fun splashDirection(impl: SplashDirectionImpl): SplashDirection

    @Binds
    fun authDirection(impl: AuthDirectionImpl): AuthDirection

    @Binds
    fun createPinDirection(impl: CreatePinDirectionImpl): CreatePinDirection

    @Binds
    fun VerifyDirection(impl: VerifyDirectionImpl): VerifyDirection

    @Binds
    fun pinCheckDirection(impl: PinCheckDirectionImpl): PinCheckDirection

    @Binds
    fun transferDirections(impl: TransferDirectionImpl): TransferDirection

    @Binds
    fun paymentDirections(impl: PaymentsDirectionImpl): PaymentsDirection

    @Binds
    fun profileDirections(impl: ProfileDirectionImpl): ProfileDirection
}