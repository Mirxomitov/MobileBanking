package uz.gita.mobilebanking.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.data.model.ui.BasicInfoData
import uz.gita.mobilebanking.data.model.ui.CardData
import uz.gita.mobilebanking.domain.use_case.CardsGetUseCase
import uz.gita.mobilebanking.domain.use_case.HomeGetBasicInfoUseCase
import uz.gita.mobilebanking.domain.use_case.HomeGetTotalBalanceUseCase
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
            MainContract.Intent.OpenProfileScreen -> direction.toProfileScreen()
            MainContract.Intent.OpenAddCardScreen -> direction.toAddCardScreen()
            MainContract.Intent.OpenWhatIsIt -> direction.toWhatIsIt()
            MainContract.Intent.OpenMyCardsScreen -> direction.toMyCardsScreen((state as MainContract.UIState.Content).cards)
            is MainContract.Intent.OpenCardDetails -> direction.toCardDetails(intent.cardData)
        }
    }

    override val container = container<MainContract.UIState, MainContract.SideEffect>(MainContract.UIState.Loading) {}

    init { fetchAllData() }

    private fun fetchAllData() {
        combine(
            cardsGetUseCase().map { it.getOrNull() },
            getTotalBalanceUseCase().map { it.getOrNull() },
            getBasicInfoUseCase().map { it.getOrNull() }
        ) { (cards, balance, info) ->
            Triple(cards, balance, info)
        }.onEach { (cards, balance, info) ->
            if (cards != null && balance != null && info != null) {
                intent {
                    reduce {
                        MainContract.UIState.Content(
                            cards = cards as List<CardData>,
                            totalBalance = balance as String,
                            firstName = (info as BasicInfoData).firstName
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}