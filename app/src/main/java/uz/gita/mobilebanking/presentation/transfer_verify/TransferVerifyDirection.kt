package uz.gita.mobilebanking.presentation.transfer_verify

import uz.gita.mobilebanking.presentation.transfer_success.TransferConfirmationScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface TransferVerifyDirection {
    suspend fun toSuccessScreen(
        amount: String,
        receiverName: String,
        receiverCardPan: String
    )

    suspend fun back()
}

@Singleton
class TransferVerifyDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : TransferVerifyDirection {
    override suspend fun toSuccessScreen(amount: String, receiverName: String, receiverCardPan: String) {
        appNavigator.addScreen(TransferConfirmationScreen(amount, receiverName, receiverCardPan))
    }

    override suspend fun back() {
        appNavigator.back()
    }
}