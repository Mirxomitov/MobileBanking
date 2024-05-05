package uz.gita.mobilebanking.presentation.verify

import uz.gita.mobilebanking.presentation.pin_create.PinCreateScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface VerifyDirection {
    suspend fun toPinCreateScreen()
    suspend fun back()
}

@Singleton
class VerifyDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : VerifyDirection {
    override suspend fun toPinCreateScreen() {
        appNavigator.replaceScreen(PinCreateScreen())
    }
    override suspend fun back() {
        appNavigator.back()
    }
}