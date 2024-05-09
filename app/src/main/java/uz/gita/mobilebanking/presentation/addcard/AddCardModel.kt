package uz.gita.mobilebanking.presentation.addcard

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.CardRepository
import javax.inject.Inject

@HiltViewModel
class AddCardModel @Inject constructor(
    private val addCardDirection: AddCardDirection,
    private val cardRepository: CardRepository,
) : ViewModel(), AddCardContract.Model {
    override fun onEventDispatchers(intent: AddCardContract.Intent) {
        when (intent) {
            AddCardContract.Intent.ToScanCardScreen -> intent { addCardDirection.toScanCardScreen() }
            AddCardContract.Intent.Back -> intent { addCardDirection.back() }
            is AddCardContract.Intent.AddCard -> {
                cardRepository.addCard(intent.cardNumber, intent.expirationDate)
            }
        }
    }

    override val container = container<AddCardContract.UIState, AddCardContract.SideEffect>(AddCardContract.UIState)
}