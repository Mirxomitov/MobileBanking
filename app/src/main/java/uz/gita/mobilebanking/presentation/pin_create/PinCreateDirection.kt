package uz.gita.mobilebanking.presentation.pin_create

import uz.gita.mobilebanking.presentation.pin_check.PinCheckScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface PinCreateDirection {
    suspend fun toPinCheckScreen(pinCode : String)
}

@Singleton
class PinCreateDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : PinCreateDirection {
    override suspend fun toPinCheckScreen(pinCode : String) {
        navigator.replaceScreen(PinCheckScreen(pinCode))
    }
}