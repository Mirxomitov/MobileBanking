package uz.gita.mobilebanking.presentation.card_theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.use_case.CardsUpdateUseCase
import uz.gita.mobilebanking.utils.MyDataLoader
import javax.inject.Inject

@HiltViewModel
class CardThemeViewModel @Inject constructor(
    private val directions: CardThemeDirections,
    private val updateCard: CardsUpdateUseCase
) : ViewModel(), CardThemeContract.ViewModel {
    override val container =
        container<CardThemeContract.UIState, CardThemeContract.SideEffect>(CardThemeContract.UIState())

    override fun onEventDispatcher(intent: CardThemeContract.Intent) = intent {
        when (intent) {
            is CardThemeContract.Intent.BackCardDetails -> {
                directions.backCardDetails(intent.data)
            }

            is CardThemeContract.Intent.UpdateCard -> {
                updateCard(intent.data.id, intent.data.name, intent.data.themeType, intent.data.isVisible)
                    .onEach { result ->
                        result.onSuccess {
                            MyDataLoader.loadCardsData()
                            intent { directions.backCardDetails(intent.data) }
                        }
                        result.onFailure { }
                    }.launchIn(viewModelScope)
            }
        }
    }

}