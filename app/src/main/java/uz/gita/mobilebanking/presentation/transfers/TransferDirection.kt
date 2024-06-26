package uz.gita.mobilebanking.presentation.transfers

import uz.gita.mobilebanking.presentation.add_template.AddTemplateScreen
import uz.gita.mobilebanking.presentation.p2p.P2PScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface TransferDirection {
    suspend fun toP2PScreen(pan: String, ownerName: String)
    suspend fun toAddTemplate()
}

@Singleton
class TransferDirectionImpl @Inject constructor(private val appNavigator: AppNavigator) : TransferDirection {
    override suspend fun toP2PScreen(pan: String, ownerName: String) {
        appNavigator.addScreen(P2PScreen(pan, ownerName))
    }
    override suspend fun toAddTemplate() {
        appNavigator.addScreen(AddTemplateScreen())
    }
}