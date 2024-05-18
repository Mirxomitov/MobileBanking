package uz.gita.mobilebanking.presentation.p2p

import uz.gita.mobilebanking.presentation.transfer_verify.TransferVerifyScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface P2PDirection {
    suspend fun toTransferVerifyScreen(token: String)
}

@Singleton
class P2PDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : P2PDirection {
    override suspend fun toTransferVerifyScreen(token: String) {
        appNavigator.addScreen(TransferVerifyScreen(token))
    }
}