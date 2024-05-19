package uz.gita.mobilebanking.presentation.p2p

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.use_case.TransferUseCase
import uz.gita.mobilebanking.utils.logger
import javax.inject.Inject

@HiltViewModel
class P2PModel @Inject constructor(
    private val transferUseCase: TransferUseCase,
    private val p2P2PDirection: P2PDirection
) : ViewModel(), P2PContract.Model {
    override fun onEventDispatcher(intent: P2PContract.Intent) {
        when (intent) {
            P2PContract.Intent.Back -> {}
            is P2PContract.Intent.Pay -> {
                 transferUseCase(intent.senderId, intent.receiverPan, intent.amount).onEach {
                    it.onSuccess { token ->
                        p2P2PDirection.toTransferVerifyScreen(token)
                    }
                    it.onFailure { logger("on failure transfer ${it.message}") }
                }.launchIn(viewModelScope)
            }

            is P2PContract.Intent.SaveReceiverData ->
                intent {
                    reduce {
                        P2PContract.UIState(
                            receiverPan = intent.receiverPan,
                            ownerName = intent.ownerName
                        )
                    }
                }

        }
    }

    override val container = container<P2PContract.UIState, P2PContract.SideEffect>(P2PContract.UIState())
}