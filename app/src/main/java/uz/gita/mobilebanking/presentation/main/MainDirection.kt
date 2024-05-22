package uz.gita.mobilebanking.presentation.main

import uz.gita.mobilebanking.data.model.ui.CardData
import uz.gita.mobilebanking.presentation.addcard.AddCardScreen
import uz.gita.mobilebanking.presentation.card_details.CardDetailsScreen
import uz.gita.mobilebanking.presentation.my_cards.MyCardsScreen
import uz.gita.mobilebanking.presentation.paynet_card.PaynetCardScreen
import uz.gita.mobilebanking.presentation.profile.ProfileScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton


interface MainDirection {
    suspend fun toProfileScreen()
    suspend fun toAddCardScreen()
    suspend fun toWhatIsIt()
    suspend fun toMyCardsScreen(listOfCards: List<CardData>)
    suspend fun toCardDetails(cardData: CardData)
}

@Singleton
class MainDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : MainDirection {
    override suspend fun toProfileScreen() =
        appNavigator.addScreen(ProfileScreen())

    override suspend fun toAddCardScreen() =
        appNavigator.addScreen(AddCardScreen())

    override suspend fun toWhatIsIt() =
        appNavigator.addScreen(PaynetCardScreen())

    override suspend fun toMyCardsScreen(listOfCards: List<CardData>) =
        appNavigator.addScreen(MyCardsScreen(listOfCards))

    override suspend fun toCardDetails(cardData: CardData) =
        appNavigator.addScreen(CardDetailsScreen(cardData))
}