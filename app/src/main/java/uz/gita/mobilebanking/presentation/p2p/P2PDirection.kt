package uz.gita.mobilebanking.presentation.p2p

import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface P2PDirection {
}

@Singleton
class P2PDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : P2PDirection {

}