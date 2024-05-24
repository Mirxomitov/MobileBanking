package uz.gita.mobilebanking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebanking.presentation.add_template.AddTemplateDirection
import uz.gita.mobilebanking.presentation.add_template.AddTemplateDirectionImpl
import uz.gita.mobilebanking.presentation.addcard.AddCardDirection
import uz.gita.mobilebanking.presentation.addcard.AddCardDirectionImpl
import uz.gita.mobilebanking.presentation.auth.AuthDirection
import uz.gita.mobilebanking.presentation.auth.AuthDirectionImpl
import uz.gita.mobilebanking.presentation.card_details.CardDetailsDirections
import uz.gita.mobilebanking.presentation.card_details.CardDetailsDirectionsImpl
import uz.gita.mobilebanking.presentation.card_theme.CardThemeDirections
import uz.gita.mobilebanking.presentation.card_theme.CardThemeDirectionsImpl
import uz.gita.mobilebanking.presentation.history.HistoryDirection
import uz.gita.mobilebanking.presentation.history.HistoryDirectionImpl
import uz.gita.mobilebanking.presentation.main.MainDirection
import uz.gita.mobilebanking.presentation.main.MainDirectionImpl
import uz.gita.mobilebanking.presentation.my_cards.MyCardsDirection
import uz.gita.mobilebanking.presentation.my_cards.MyCardsDirectionImpl
import uz.gita.mobilebanking.presentation.p2p.P2PDirection
import uz.gita.mobilebanking.presentation.p2p.P2PDirectionImpl
import uz.gita.mobilebanking.presentation.payments.PaymentsDirection
import uz.gita.mobilebanking.presentation.payments.PaymentsDirectionImpl
import uz.gita.mobilebanking.presentation.paynet_card.PaynetCardDirection
import uz.gita.mobilebanking.presentation.paynet_card.PaynetCardDirectionImpl
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
import uz.gita.mobilebanking.presentation.transfer_verify.TransferVerifyDirection
import uz.gita.mobilebanking.presentation.transfer_verify.TransferVerifyDirectionImpl
import uz.gita.mobilebanking.presentation.transfers.TransferDirection
import uz.gita.mobilebanking.presentation.transfers.TransferDirectionImpl
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

    @Binds
    fun paynetCardDirection(impl: PaynetCardDirectionImpl): PaynetCardDirection

    @Binds
    fun myCardsDirection(impl: MyCardsDirectionImpl): MyCardsDirection

    @Binds
    fun transferVerifyDirection(impl: TransferVerifyDirectionImpl): TransferVerifyDirection

    @Binds
    fun p2pDirection(impl: P2PDirectionImpl): P2PDirection

    @Binds
    fun historyDirection(impl: HistoryDirectionImpl): HistoryDirection

    @Binds
    fun cardThemeDirection(impl: CardThemeDirectionsImpl): CardThemeDirections

    @Binds
    fun cardDetailsDirections(impl: CardDetailsDirectionsImpl): CardDetailsDirections

    @Binds
    fun addTemplateDirection(impl: AddTemplateDirectionImpl): AddTemplateDirection
}