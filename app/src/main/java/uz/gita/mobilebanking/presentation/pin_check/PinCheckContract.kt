package uz.gita.mobilebanking.presentation.pin_check

import org.orbitmvi.orbit.ContainerHost

interface PinCheckContract {
    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        data object InitState : UIState
    }

    sealed interface SideEffect {}
    sealed interface Intent {
        data object ToMainScreen : Intent
    }
}