package uz.gita.mobilebanking.presentation.pin_create

import org.orbitmvi.orbit.ContainerHost

interface PinCreateContract {
    sealed interface Model : ContainerHost<PinCreateContract.UIState, PinCreateContract.SideEffect> {
        fun onEventDispatcher(intent: PinCreateContract.Intent)
    }

    sealed interface Intent {
        data class ToPinCheckScreen(val pinCode : String) : Intent
    }

    data class UIState(
        val phoneNumber : String = "",
    )

    sealed interface SideEffect {}
}