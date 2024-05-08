package uz.gita.mobilebanking.presentation.addcard

import uz.gita.mobilebanking.presentation.scancard.ScanCardScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface AddCardDirection {
    suspend fun toScanCardScreen()
    suspend fun back()
}

@Singleton
class AddCardDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : AddCardDirection {
    override suspend fun toScanCardScreen() {
        appNavigator.addScreen(ScanCardScreen())
    }

    override suspend fun back() {
        appNavigator.back()
    }
}

