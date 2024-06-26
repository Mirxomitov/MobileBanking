package uz.gita.mobilebanking.presentation.profile

import uz.gita.mobilebanking.presentation.auth.AuthScreen
import uz.gita.mobilebanking.presentation.map.MapScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface ProfileDirection {
    suspend fun back()
    suspend fun toAuthScreen()
    suspend fun toMapScreen()
}

@Singleton
class ProfileDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : ProfileDirection {
    override suspend fun back() { appNavigator.back() }
    override suspend fun toAuthScreen() { appNavigator.replaceAll(AuthScreen()) }
    override suspend fun toMapScreen() { appNavigator.addScreen(MapScreen()) }
}