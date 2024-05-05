package uz.gita.mobilebanking.presentation.pin_check

import org.orbitmvi.orbit.ContainerHost

interface PinCheckContract {
    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        var phoneNumber: String = "",
        var pinCode : String = "",
    )

    sealed interface SideEffect {}
    sealed interface Intent {
        data class GetPinCode(val pinCode: String) : Intent
        data object ToMainScreen : Intent
        data object GetPhoneNumber : Intent
    }
}