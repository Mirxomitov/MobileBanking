package uz.gita.mobilebanking.presentation.pin_check

import uz.gita.mobilebanking.presentation.tabs.BottomNavigation
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface PinCheckDirection {
    suspend fun toMainScreen()
}

@Singleton
class PinCheckDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : PinCheckDirection {
    override suspend fun toMainScreen() {
        appNavigator.replaceScreen(BottomNavigation())
    }
}