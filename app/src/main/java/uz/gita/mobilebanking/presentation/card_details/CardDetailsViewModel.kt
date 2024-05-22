package uz.gita.mobilebanking.presentation.card_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.use_case.CardDeleteUseCase
import javax.inject.Inject

@HiltViewModel
class CardDetailsViewModel @Inject constructor(
    private val directions: CardDetailsDirections,
    private val deleteCard: CardDeleteUseCase
) : ViewModel(), CardDetailsContract.ViewModel {
    override val container =
        container<CardDetailsContract.UIState, CardDetailsContract.SideEffect>(CardDetailsContract.UIState())

    override fun onEventDispatcher(intent: CardDetailsContract.Intent) = intent {
        when (intent) {
            CardDetailsContract.Intent.BackToHomeScreen -> {
                directions.backToHomeScreen()
            }

            is CardDetailsContract.Intent.DeleteCard -> {
                deleteCard(intent.id).onEach { result ->
                    result.onSuccess {
                        intent { directions.backToHomeScreen() }
                    }
                    result.onFailure {

                    }
                }.launchIn(viewModelScope)
            }

            CardDetailsContract.Intent.NavigateToPaymentPage -> {
                postSideEffect(CardDetailsContract.SideEffect.NavigateToPaymentPage)
            }

            CardDetailsContract.Intent.NavigateToTransferPage -> {
                postSideEffect(CardDetailsContract.SideEffect.NavigateToTransferPage)
            }

            is CardDetailsContract.Intent.NavigateToCardTheme -> {
                directions.navigateCardTheme(intent.data)
            }
        }
    }

}