package uz.gita.mobilebanking.presentation.pin

import uz.gita.mobilebanking.presentation.main_navigation.MainNavigationScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface PinDirection {
    suspend fun toBottomNavigation()
}

@Singleton
class PinDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : PinDirection {
    override suspend fun toBottomNavigation() {
        appNavigator.replaceScreen(MainNavigationScreen())
    }
}