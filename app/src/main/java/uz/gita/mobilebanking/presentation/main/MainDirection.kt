package uz.gita.mobilebanking.presentation.main

import uz.gita.mobilebanking.presentation.addcard.AddCardScreen
import uz.gita.mobilebanking.presentation.profile.ProfileScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton


interface MainDirection {
    suspend fun toProfileScreen()
    suspend fun toAddCardScreen()
}

@Singleton
class MainDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : MainDirection {
    override suspend fun toProfileScreen() {
        appNavigator.addScreen(ProfileScreen())
    }

    override suspend fun toAddCardScreen() {
        appNavigator.addScreen(AddCardScreen())
    }
}