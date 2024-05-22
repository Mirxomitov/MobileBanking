package uz.gita.mobilebanking.presentation.my_cards

import uz.gita.mobilebanking.presentation.addcard.AddCardScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject

interface MyCardsDirection {
    suspend fun back()

    suspend fun toAddCardScreen()
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
}