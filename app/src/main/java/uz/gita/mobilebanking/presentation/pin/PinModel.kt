package uz.gita.mobilebanking.presentation.pin

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.repositories.RegistrationRepository
import javax.inject.Inject

@HiltViewModel
class PinModel @Inject constructor(
    private val pinDirection: PinDirection,
    private val registrationRepository: RegistrationRepository
) : ViewModel(), PinContract.Model {
    override fun onEventDispatcher(intent: PinContract.Intent) {
        when (intent) {
            is PinContract.Intent.ToMainScreen -> {
                intent { pinDirection.toBottomNavigation() }
            }
        }
    }

    override val container = container<PinContract.UIState, PinContract.SideEffect>(
        PinContract.UIState(
            pinCode = registrationRepository.pinCode(),
            phoneNumber = registrationRepository.phoneNumber()
        )
    )
}