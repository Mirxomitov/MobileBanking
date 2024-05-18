package uz.gita.mobilebanking.presentation.transfer_verify

import uz.gita.mobilebanking.presentation.tabs.BottomNavigation
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface TransferVerifyDirection {
    suspend fun toMainScreen()
    suspend fun back()
}

@Singleton
class TransferVerifyDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : TransferVerifyDirection {
    override suspend fun toMainScreen() {
        appNavigator.backUntil(BottomNavigation())
    }

    override suspend fun back() {
        appNavigator.back()
    }
}