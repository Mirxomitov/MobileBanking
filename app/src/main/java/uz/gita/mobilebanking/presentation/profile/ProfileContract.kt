package uz.gita.mobilebanking.presentation.profile

import org.orbitmvi.orbit.ContainerHost

interface ProfileContract {

    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatchers(intent : Intent)
    }

    sealed interface UIState {
        data object InitState : UIState
    }
    sealed interface Intent {
        data object Back : Intent
    }
    sealed interface SideEffect {}
}