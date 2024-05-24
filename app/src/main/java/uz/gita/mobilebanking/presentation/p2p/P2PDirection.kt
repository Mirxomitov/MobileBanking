package uz.gita.mobilebanking.presentation.p2p

import uz.gita.mobilebanking.presentation.addcard.AddCardScreen
import uz.gita.mobilebanking.presentation.transfer_verify.TransferVerifyScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface P2PDirection {
    suspend fun toTransferVerifyScreen(token: String, amount: String, receiverName: String, receiverPan: String)
    suspend fun toAddCardScreen()

    suspend fun back()
}

@Singleton
class P2PDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : P2PDirection {
    override suspend fun toTransferVerifyScreen(
        token: String,
        amount: String,
        receiverName: String,
        receiverPan: String
    ) {
        appNavigator.addScreen(TransferVerifyScreen(token, amount, receiverName, receiverPan))
    }

    override suspend fun toAddCardScreen() {
        appNavigator.addScreen(AddCardScreen())
    }

    override suspend fun back() {
        appNavigator.back()
    }
}