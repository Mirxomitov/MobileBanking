package uz.gita.mobilebanking.presentation.splash

import uz.gita.mobilebanking.presentation.auth.AuthScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface SplashDirection {
    suspend fun toNext()
}

@Singleton
class SplashDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : SplashDirection {
    override suspend fun toNext() {
        appNavigator.replaceScreen(AuthScreen())
    }
}