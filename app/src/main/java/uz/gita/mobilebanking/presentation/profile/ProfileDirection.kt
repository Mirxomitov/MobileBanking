package uz.gita.mobilebanking.presentation.profile

import uz.gita.mobilebanking.presentation.auth.AuthScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface ProfileDirection {
    suspend fun back()
    suspend fun toAuthScreen()
}

@Singleton
class ProfileDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : ProfileDirection {
    override suspend fun back() {
        appNavigator.back()
    }

    override suspend fun toAuthScreen() {
        appNavigator.replaceAll(AuthScreen())
    }
}