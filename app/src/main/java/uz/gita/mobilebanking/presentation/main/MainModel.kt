package uz.gita.mobilebanking.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.CardRepository
import uz.gita.mobilebanking.utils.logger
import javax.inject.Inject

@HiltViewModel
class MainModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val direction: MainDirection,
) : MainContract.Model, ViewModel() {

    private fun getCards() {
        cardRepository.getCards().onEach {
            it.onSuccess {
                logger("MainModel.getCards.success ${it.size} $it")
                //container.stateFlow.value.cards = it
                intent { reduce { MainContract.UIState(it) } }
            }
            it.onFailure {
                container.stateFlow.value.cards = emptyList()
            }
        }.launchIn(viewModelScope)
    }

    override fun onEventDispatcher(intent: MainContract.Intent) = intent {
        when (intent) {
            MainContract.Intent.OpenProfileScreen -> direction.toProfileScreen()
            MainContract.Intent.OpenAddCardScreen -> direction.toAddCardScreen()
            MainContract.Intent.OpenWhatIsIt -> direction.toWhatIsIt()
            MainContract.Intent.Init -> getCards()
        }
    }

    override val container = container<MainContract.UIState, MainContract.SideEffect>(MainContract.UIState()) {}
}