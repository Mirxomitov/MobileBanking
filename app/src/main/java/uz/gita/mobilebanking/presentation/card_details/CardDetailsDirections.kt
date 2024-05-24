package uz.gita.mobilebanking.presentation.card_details

import uz.gita.mobilebanking.data.model.CardData
import uz.gita.mobilebanking.presentation.card_theme.CardThemeScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject

interface CardDetailsDirections {
    suspend fun backToHomeScreen()
    suspend fun navigateCardTheme(data: CardData)
}

class CardDetailsDirectionsImpl @Inject constructor(
    private val navigator: AppNavigator
) : CardDetailsDirections {
    override suspend fun backToHomeScreen() =
        navigator.back()

    override suspend fun navigateCardTheme(data: CardData) =
        navigator.replaceScreen(CardThemeScreen(data))
}