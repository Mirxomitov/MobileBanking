package uz.gita.mobilebanking.presentation.transactions

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class TransferModel @Inject constructor(
    private val direction: TransferDirection
) : TransferContract.Model, ViewModel() {
    override fun onEventDispatchers(intent: TransferContract.Intent) {
        when (intent) {
            TransferContract.Intent.ToP2PScreen -> {
                intent { direction.toP2PScreen() }
            }
        }
    }

    override val container =
        container<TransferContract.UIState, TransferContract.SideEffect>(TransferContract.UIState.InitState)
}