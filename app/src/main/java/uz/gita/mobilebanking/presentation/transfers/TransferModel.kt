package uz.gita.mobilebanking.presentation.transfers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.use_case.GetCardOwnerByPan
import javax.inject.Inject

@HiltViewModel
class TransferModel @Inject constructor(
    private val direction: TransferDirection,
    private val getCardOwnerByPan: GetCardOwnerByPan

) : TransferContract.Model, ViewModel() {
    override fun onEventDispatchers(intent: TransferContract.Intent) {
        when (intent) {
            is TransferContract.Intent.ToP2PScreen -> intent { direction.toP2PScreen(intent.pan, intent.ownerName) }
            is TransferContract.Intent.GetCardOwnerByCardNumber -> {
                getCardOwnerByPan(intent.pan).onEach {
                    it.onSuccess { ownerName ->
                        intent {
                            reduce { TransferContract.UIState(ownerName = ownerName, pan = intent.pan) }
                        }
                    }
                }.launchIn(viewModelScope)
            }

            TransferContract.Intent.ClearOwnerName -> intent { reduce { TransferContract.UIState(ownerName = "") } }
        }
    }

    override val container = container<TransferContract.UIState, TransferContract.SideEffect>(TransferContract.UIState())
}