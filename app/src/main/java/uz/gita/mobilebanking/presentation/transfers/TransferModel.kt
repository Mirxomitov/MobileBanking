package uz.gita.mobilebanking.presentation.transfers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.use_case.CardGetOwnerByPan
import uz.gita.mobilebanking.domain.use_case.GetPayedCardsUseCase
import uz.gita.mobilebanking.domain.use_case.GetTemplatesCardsUseCase
import uz.gita.mobilebanking.utils.logger
import javax.inject.Inject

@HiltViewModel
class TransferModel @Inject constructor(
    private val direction: TransferDirection,
    private val cardGetOwnerByPan: CardGetOwnerByPan,
    private val getPayedCardsUseCase: GetPayedCardsUseCase,
    private val getTemplatesCardsUseCase: GetTemplatesCardsUseCase,

    ) : TransferContract.Model, ViewModel() {

    init {
        showPayedCards()
    }


    override fun onEventDispatchers(intent: TransferContract.Intent) {
        when (intent) {
            is TransferContract.Intent.ToP2PScreen -> intent { direction.toP2PScreen(intent.pan, intent.ownerName) }
            is TransferContract.Intent.GetCardOwnerByCardNumber -> {
                cardGetOwnerByPan(intent.pan).onEach {
                    it.onSuccess { ownerName ->
                        intent {
                            reduce {
                                TransferContract.UIState(
                                    ownerName = ownerName,
                                    pan = intent.pan,
                                    payedCards = state.payedCards
                                )
                            }
                        }
                    }
                    it.onFailure {
                        intent {
                            reduce {
                                TransferContract.UIState(
                                    ownerName = "",
                                    pan = intent.pan,
                                    payedCards = state.payedCards
                                )
                            }
                        }
                    }
                }.launchIn(viewModelScope)
            }

            TransferContract.Intent.ClearOwnerName -> intent {
                reduce {
                    TransferContract.UIState(
                        ownerName = "",
                        pan = "",
                        payedCards = state.payedCards
                    )
                }
            }

            TransferContract.Intent.GetPayedCards -> {
                showPayedCards()
            }

            is TransferContract.Intent.GetTemplateCards -> {
                showTemplateCards()
            }

            TransferContract.Intent.AddCardTemplate -> {
                intent{ direction.toAddTemplate()}
            }
        }
    }

    override val container =
        container<TransferContract.UIState, TransferContract.SideEffect>(TransferContract.UIState())

    override fun initCardNumber(cardNumber: String) {
        intent { reduce { TransferContract.UIState(cardNumber = cardNumber) } }
    }

    private fun showPayedCards() {
        viewModelScope.launch(Dispatchers.IO) {
            val ls = getPayedCardsUseCase()

            intent {
                reduce {
                    logger("payed cards TransferModel: ${ls}")
                    TransferContract.UIState(
                        ownerName = state.ownerName,
                        pan = state.pan,
                        payedCards = ls,
                        templateCards = state.templateCards
                    )
                }
            }
        }
    }

    private fun showTemplateCards() {
        viewModelScope.launch(Dispatchers.IO) {
            val ls = getTemplatesCardsUseCase()

            intent {
                reduce {
                    logger("payed cards TransferModel: ${ls + state.templateCards}")
                    TransferContract.UIState(
                        ownerName = state.ownerName,
                        pan = state.pan,
                        payedCards = state.payedCards,
                        templateCards = ls
                    )
                }
            }
        }
    }
}