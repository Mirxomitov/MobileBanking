package uz.gita.mobilebanking.presentation.p2p

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class P2PModel @Inject constructor() : ViewModel(), P2PContract.Model {
    override fun onEventDispatcher(intent: P2PContract.Intent) {
        when (intent) {
            P2PContract.Intent.Back -> {}
            P2PContract.Intent.Pay -> {}
        }
    }

    override val container = container<P2PContract.UIState, P2PContract.SideEffect>(P2PContract.UIState())
}