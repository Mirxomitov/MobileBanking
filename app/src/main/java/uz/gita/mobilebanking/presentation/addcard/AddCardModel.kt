package uz.gita.mobilebanking.presentation.addcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.CardRepository
import uz.gita.mobilebanking.domain.use_case.AddCardUseCase
import uz.gita.mobilebanking.utils.logger
import javax.inject.Inject

@HiltViewModel
class AddCardModel @Inject constructor(
    private val addCardDirection: AddCardDirection,
    private val addCardUseCase: AddCardUseCase
) : ViewModel(), AddCardContract.Model {
    override fun onEventDispatchers(intent: AddCardContract.Intent) {
        when (intent) {
            AddCardContract.Intent.ToScanCardScreen -> intent { addCardDirection.toScanCardScreen() }
            AddCardContract.Intent.Back -> intent { addCardDirection.back() }
            is AddCardContract.Intent.AddCard -> {
                addCardUseCase(intent.cardNumber, intent.expirationDate).onEach {
                    it.onSuccess {
                        addCardDirection.back()
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