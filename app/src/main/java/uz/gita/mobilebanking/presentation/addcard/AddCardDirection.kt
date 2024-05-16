package uz.gita.mobilebanking.presentation.addcard

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import uz.gita.mobilebanking.presentation.read_card_number.ReadCardNumberScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface AddCardDirection {
    suspend fun toScanCardScreen()
    suspend fun back()
}

@Singleton
@OptIn(ExperimentalGetImage::class)
class AddCardDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : AddCardDirection {
    override suspend fun toScanCardScreen() {
        appNavigator.addScreen(ReadCardNumberScreen())
    }

    override suspend fun back() {
        appNavigator.back()
    }
}

