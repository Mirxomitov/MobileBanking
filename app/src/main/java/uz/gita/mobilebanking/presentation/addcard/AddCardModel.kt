package uz.gita.mobilebanking.presentation.addcard

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AddCardModel @Inject constructor(
    private val addCardDirection: AddCardDirection
) : ViewModel(), AddCardContract.Model {
    override fun onEventDispatchers(intent: AddCardContract.Intent) {
        when (intent) {
            AddCardContract.Intent.ToScanCardScreen -> intent { addCardDirection.toScanCardScreen() }
            AddCardContract.Intent.Back -> intent { addCardDirection.back() }
        }
    }

    override val container = container<AddCardContract.UIState, AddCardContract.SideEffect>(AddCardContract.UIState)
}