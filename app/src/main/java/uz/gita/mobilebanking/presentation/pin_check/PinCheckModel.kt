package uz.gita.mobilebanking.presentation.pin_check

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class PinCheckModel @Inject constructor(
    private val pinDirection: PinCheckDirection
) : ViewModel(), PinCheckContract.Model {
    override fun onEventDispatcher(intent: PinCheckContract.Intent) {
        when (intent) {
            PinCheckContract.Intent.ToMainScreen -> {
                intent { pinDirection.toMainScreen() }
            }
        }
    }

    override val container =
        container<PinCheckContract.UIState, PinCheckContract.SideEffect>(PinCheckContract.UIState.InitState)
}