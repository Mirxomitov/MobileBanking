package uz.gita.mobilebanking.presentation.my_cards

import uz.gita.mobilebanking.data.model.CardData
import uz.gita.mobilebanking.presentation.addcard.AddCardScreen
import uz.gita.mobilebanking.presentation.card_details.CardDetailsScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject

interface MyCardsDirection {
    suspend fun back()
    suspend fun toAddCardScreen()
    suspend fun toDetailsScreen(cardData: CardData)
}

class MyCardsDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : MyCardsDirection {
    override suspend fun back() {
        appNavigator.back()
    }

    override suspend fun toAddCardScreen() {
        appNavigator.addScreen(AddCardScreen())
    }

    override suspend fun toDetailsScreen(cardData: CardData) {
        appNavigator.addScreen(CardDetailsScreen(cardData))
    }
}