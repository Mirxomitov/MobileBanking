package uz.gita.mobilebanking.presentation.pin_create

import uz.gita.mobilebanking.presentation.pin_check.PinCheckScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface CreatePinDirection {
    suspend fun toCheckPinScreen()
}

@Singleton
class CreatePinDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : CreatePinDirection {
    override suspend fun toCheckPinScreen() {
        navigator.replaceScreen(PinCheckScreen())
    }
}