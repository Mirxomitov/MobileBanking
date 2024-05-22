package uz.gita.mobilebanking.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.use_case.CardsGetUseCase
import uz.gita.mobilebanking.domain.use_case.HomeGetBasicInfoUseCase
import uz.gita.mobilebanking.domain.use_case.HomeGetTotalBalanceUseCase
import uz.gita.mobilebanking.utils.logger
import javax.inject.Inject

@HiltViewModel
class MainModel @Inject constructor(
    private val cardsGetUseCase: CardsGetUseCase,
    private val direction: MainDirection,
    private val getTotalBalanceUseCase: HomeGetTotalBalanceUseCase,
    private val getBasicInfoUseCase: HomeGetBasicInfoUseCase
) : MainContract.Model, ViewModel() {

    override fun onEventDispatcher(intent: MainContract.Intent) = intent {
        when (intent) {
            MainContract.Intent.Init -> {
                getCards()
                getTotalBalance()
                getBasicInfo()
            }

            MainContract.Intent.OpenProfileScreen -> direction.toProfileScreen()
            MainContract.Intent.OpenAddCardScreen -> direction.toAddCardScreen()
            MainContract.Intent.OpenWhatIsIt -> direction.toWhatIsIt()
            MainContract.Intent.OpenMyCardsScreen -> direction.toMyCardsScreen(container.stateFlow.value.cards)
        }
    }

    override val container = container<MainContract.UIState, MainContract.SideEffect>(MainContract.UIState()) {}

    private fun getCards() {
        cardsGetUseCase().onEach {
            it.onSuccess {
                intent {
                    reduce {
                        MainContract.UIState(
                            cards = it,
                            totalBalance = state.totalBalance,
                            firstName = state.firstName,
                        )
                    }
                }
            }
            it.onFailure {
                intent {
                    reduce {
                        MainContract.UIState(
                            cards = emptyList(),
                            totalBalance = state.totalBalance,
                            firstName = state.firstName,
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTotalBalance() {
        getTotalBalanceUseCase().onEach {
            it.onSuccess {
                logger("MainModel.totalBalance.success $it")
                intent {
                    reduce {
                        MainContract.UIState(
                            cards = state.cards,
                            totalBalance = it,
                            firstName = state.firstName,
                        )
                    }
                }
            }
            it.onFailure {
                intent {
                    reduce {
                        MainContract.UIState(
                            cards = state.cards,
                            totalBalance = "0",
                            firstName = state.firstName,
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getBasicInfo() {
        getBasicInfoUseCase().onEach {
            it.onSuccess {
                intent {
                    reduce {
                        MainContract.UIState(
                            cards = state.cards,
                            totalBalance = state.totalBalance,
                            firstName = it.firstName,
                        )
                    }
                }
            }
            it.onFailure {
                logger("MainModel.basicInfo.failure")
            }
        }.launchIn(viewModelScope)
    }
}