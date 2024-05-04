package uz.gita.mobilebanking.presentation.pin_create

import org.orbitmvi.orbit.ContainerHost

interface CreatePinContract {
    sealed interface Model : ContainerHost<CreatePinContract.UIState, CreatePinContract.SideEffect> {
        fun onEventDispatcher(intent: CreatePinContract.Intent)
    }

    sealed interface Intent {
        data object ToPinCheckScreen : Intent
    }

    sealed interface UIState {
        data object InitState : UIState
    }

    sealed interface SideEffect {}
}