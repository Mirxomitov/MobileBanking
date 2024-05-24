package uz.gita.mobilebanking.presentation.pin_create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.repositories.RegistrationRepository
import javax.inject.Inject

@HiltViewModel
class PinCreateModel @Inject constructor(
    private val direction: PinCreateDirection,
    private val registrationRepository: RegistrationRepository
) : ViewModel(), PinCreateContract.Model {
    override fun onEventDispatcher(intent: PinCreateContract.Intent) {
        when (intent) {
            is PinCreateContract.Intent.ToPinCheckScreen -> {
                viewModelScope.launch {
                    direction.toPinCheckScreen(intent.pinCode)
                }
            }
        }
    }

    override val container =
        container<PinCreateContract.UIState, PinCreateContract.SideEffect>(
            PinCreateContract.UIState(
                registrationRepository.phoneNumber()
            )
        )
}