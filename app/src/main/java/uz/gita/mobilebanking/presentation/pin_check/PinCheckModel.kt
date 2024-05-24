package uz.gita.mobilebanking.presentation.pin_check

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.repositories.RegistrationRepository
import javax.inject.Inject

@HiltViewModel
class PinCheckModel @Inject constructor(
    private val registrationRepository: RegistrationRepository,
    private val pinDirection: PinCheckDirection
) : ViewModel(), PinCheckContract.Model {
    override fun onEventDispatcher(intent: PinCheckContract.Intent) {
        when (intent) {
            is PinCheckContract.Intent.ToMainScreen -> {
                registrationRepository.pinCode(container.stateFlow.value.pinCode) // save pin code to shared preference
                intent { pinDirection.toMainScreen() }
            }

            is PinCheckContract.Intent.GetPinCode -> {
                container.stateFlow.value.pinCode = intent.pinCode // get from create pin screen
            }

            is PinCheckContract.Intent.GetPhoneNumber -> {
                container.stateFlow.value.phoneNumber =
                    registrationRepository.phoneNumber() // get phone number from repository
            }
        }
    }

    override val container =
        container<PinCheckContract.UIState, PinCheckContract.SideEffect>(PinCheckContract.UIState())
}