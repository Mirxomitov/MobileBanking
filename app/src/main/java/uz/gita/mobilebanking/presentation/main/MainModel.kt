package uz.gita.mobilebanking.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.data.model.ui.BasicInfoData
import uz.gita.mobilebanking.data.model.ui.CardData
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
    init {
        logger("Main Screen Model Initialization")
        fetchAllData()
    }

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

    private fun fetchAllData() {
        combine(
            cardsGetUseCase(),
            getTotalBalanceUseCase(),
            getBasicInfoUseCase()
        ) { (cards, balance, info) ->
            Triple(cards, balance, info)
        }.onEach { (cards, balance, info) ->
            if (cards.isSuccess && balance.isSuccess && info.isSuccess) {
                intent {
                    reduce {
                        @Suppress("UNCHECKED_CAST")
                        MainContract.UIState.Content(
                            cards = cards.getOrNull() as List<CardData>,
                            totalBalance = balance.getOrNull() as String,
                            firstName = (info.getOrNull() as BasicInfoData).firstName
                        )
                    }
                }
            } else {
                // errors
                cards.getOrElse {}
                balance.getOrElse {}
                info.getOrElse {}
            }
        }.launchIn(viewModelScope)
    }
}