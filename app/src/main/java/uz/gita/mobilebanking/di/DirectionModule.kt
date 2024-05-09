package uz.gita.mobilebanking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebanking.presentation.addcard.AddCardDirection
import uz.gita.mobilebanking.presentation.addcard.AddCardDirectionImpl
import uz.gita.mobilebanking.presentation.auth.AuthDirection
import uz.gita.mobilebanking.presentation.auth.AuthDirectionImpl
import uz.gita.mobilebanking.presentation.main.MainDirection
import uz.gita.mobilebanking.presentation.main.MainDirectionImpl
import uz.gita.mobilebanking.presentation.payments.PaymentsDirection
import uz.gita.mobilebanking.presentation.payments.PaymentsDirectionImpl
import uz.gita.mobilebanking.presentation.pin.PinDirection
import uz.gita.mobilebanking.presentation.pin.PinDirectionImpl
import uz.gita.mobilebanking.presentation.pin_check.PinCheckDirection
import uz.gita.mobilebanking.presentation.pin_check.PinCheckDirectionImpl
import uz.gita.mobilebanking.presentation.pin_create.PinCreateDirection
import uz.gita.mobilebanking.presentation.pin_create.PinCreateDirectionImpl
import uz.gita.mobilebanking.presentation.profile.ProfileDirection
import uz.gita.mobilebanking.presentation.profile.ProfileDirectionImpl
import uz.gita.mobilebanking.presentation.read_card_number.ReadCardNumberDirection
import uz.gita.mobilebanking.presentation.read_card_number.ReadCardNumberDirectionImpl
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
    fun pinDirection(impl: PinDirectionImpl): PinDirection

    @Binds
    fun pinCreateDirection(impl: PinCreateDirectionImpl): PinCreateDirection

    @Binds
    fun pinCheckDirection(impl: PinCheckDirectionImpl): PinCheckDirection

    @Binds
    fun verifyDirection(impl: VerifyDirectionImpl): VerifyDirection

    @Binds
    fun transferDirection(impl: TransferDirectionImpl): TransferDirection

    @Binds
    fun paymentDirection(impl: PaymentsDirectionImpl): PaymentsDirection

    @Binds
    fun profileDirection(impl: ProfileDirectionImpl): ProfileDirection

    @Binds
    fun mainDirection(impl: MainDirectionImpl): MainDirection

    @Binds
    fun addCardDirection(impl: AddCardDirectionImpl): AddCardDirection

    @Binds
    fun addReadCardNumberDirection(impl: ReadCardNumberDirectionImpl): ReadCardNumberDirection


}