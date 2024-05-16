package uz.gita.mobilebanking.presentation.paynet_card

import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface PaynetCardDirection {
    suspend fun back ()
}

@Singleton
class PaynetCardDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : PaynetCardDirection {
    override suspend fun back() {
        appNavigator.back()
    }
}