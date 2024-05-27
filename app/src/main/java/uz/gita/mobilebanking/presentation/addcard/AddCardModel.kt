package uz.gita.mobilebanking.presentation.addcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.use_case.CardAddUseCase
import uz.gita.mobilebanking.utils.toLog
import javax.inject.Inject

@HiltViewModel
class AddCardModel @Inject constructor(
    private val addCardDirection: AddCardDirection,
    private val cardAddUseCase: CardAddUseCase
) : ViewModel(), AddCardContract.Model {
    override fun onEventDispatchers(intent: AddCardContract.Intent) {
        when (intent) {
            AddCardContract.Intent.ToScanCardScreen -> intent { addCardDirection.toScanCardScreen() }
            AddCardContract.Intent.Back -> intent { addCardDirection.back() }
            is AddCardContract.Intent.AddCard -> {
                cardAddUseCase(intent.cardNumber, intent.expirationDate).onEach {
                    it.onSuccess {
                        addCardDirection.back()
                    }
                    it.onFailure {
                        it.message?.toLog("Error Message: AddCardContract")
                    }
                }.launchIn(viewModelScope)
            }

            is AddCardContract.Intent.SaveCard -> {
                container.stateFlow.value.cardNumber = intent.cardNumber
                container.stateFlow.value.expirationDate = intent.expirationDate
            }
        }
    }

    override val container = container<AddCardContract.UIState, AddCardContract.SideEffect>(AddCardContract.UIState())
}