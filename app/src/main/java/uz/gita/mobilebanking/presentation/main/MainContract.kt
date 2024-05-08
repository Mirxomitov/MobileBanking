package uz.gita.mobilebanking.presentation.main

import org.orbitmvi.orbit.ContainerHost

interface MainContract {
    sealed interface Model : ContainerHost<UIState, SideEffect>{
        fun onEventDispatcher(intent : Intent)
    }
    sealed interface UIState {
        data object InitState : UIState
    }
    sealed interface SideEffect {}
    sealed interface Intent {
        data object OpenProfileScreen : Intent
        data object OpenAddCardScreen : Intent
    }
}