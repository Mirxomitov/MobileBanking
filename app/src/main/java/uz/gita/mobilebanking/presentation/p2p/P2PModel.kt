package uz.gita.mobilebanking.presentation.p2p

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.use_case.CardsGetUseCase
import uz.gita.mobilebanking.domain.use_case.TransferUseCase
import uz.gita.mobilebanking.utils.logger
import javax.inject.Inject

@HiltViewModel
class P2PModel @Inject constructor(
    private val transferUseCase: TransferUseCase,
    private val p2PDirection: P2PDirection,
    private val cardsGetUseCase: CardsGetUseCase
) : ViewModel(), P2PContract.Model {

    init {
        getCards()
    }

    override fun onEventDispatcher(intent: P2PContract.Intent) {
        when (intent) {
            is P2PContract.Intent.Pay -> {
                transferUseCase(intent.senderId, intent.receiverPan, intent.amount).onEach {
                    it.onSuccess { token ->
                        p2PDirection.toTransferVerifyScreen(token)
                    }
                    it.onFailure { logger("on failure transfer ${it.message}") }
                }.launchIn(viewModelScope)
            }

            is P2PContract.Intent.SaveReceiverData -> {
                intent {
                    reduce {
                        P2PContract.UIState(
                            cards = this.state.cards,
                            receiverPan = intent.receiverPan,
                            ownerName = intent.ownerName
                        )
                    }
                }
            }

            P2PContract.Intent.AddCard -> intent { p2PDirection.toAddCardScreen() }
            P2PContract.Intent.Back -> intent { p2PDirection.back() }
        }
    }

    private fun getCards() {
        cardsGetUseCase().onEach {
            it.onSuccess {
                intent {
                    reduce {
                        P2PContract.UIState(
                            cards = it,
                            receiverPan = this.state.receiverPan,
                            ownerName = this.state.ownerName,
                        )
                    }
                }
            }
            it.onFailure { }
        }.launchIn(viewModelScope)
    }

    override val container =
        container<P2PContract.UIState, P2PContract.SideEffect>(P2PContract.UIState())
}