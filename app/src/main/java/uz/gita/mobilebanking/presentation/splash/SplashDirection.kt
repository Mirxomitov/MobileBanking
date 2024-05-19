package uz.gita.mobilebanking.presentation.splash

import uz.gita.mobilebanking.presentation.auth.AuthScreen
import uz.gita.mobilebanking.presentation.pin.PinScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface SplashDirection {
    suspend fun toAuthScreen()
    suspend fun toPinScreen()
}

@Singleton
class SplashDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : SplashDirection {
    override suspend fun toAuthScreen() {
        appNavigator.replaceScreen(AuthScreen())
    }

    override suspend fun toPinScreen() {
        appNavigator.replaceScreen(PinScreen())
    }
}