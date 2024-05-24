package uz.gita.mobilebanking.presentation.card_theme

import uz.gita.mobilebanking.data.model.CardData
import uz.gita.mobilebanking.presentation.card_details.CardDetailsScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject

interface CardThemeDirections {
    suspend fun backCardDetails(data: CardData)
}

class CardThemeDirectionsImpl @Inject constructor(
    private val navigator: AppNavigator
) : CardThemeDirections {
    override suspend fun backCardDetails(data: CardData) =
        navigator.replaceScreen(CardDetailsScreen(data))

}