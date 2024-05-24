package uz.gita.mobilebanking.presentation.add_template

import uz.gita.mobilebanking.presentation.p2p.P2PScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject

interface AddTemplateDirection {
    suspend fun back()
    suspend fun toP2PScreen(receiverPan: String, ownerName: String)
}

class AddTemplateDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : AddTemplateDirection {
    override suspend fun back() {
        appNavigator.back()
    }

    override suspend fun toP2PScreen(receiverPan: String, ownerName: String) {
        appNavigator.addScreen(P2PScreen(receiverPan = receiverPan, ownerName = ownerName))
    }
}