package uz.gita.mobilebanking.presentation.splash

import org.orbitmvi.orbit.ContainerHost


sealed interface SplashContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        data object InitState : UIState
    }
    sealed interface SideEffect {}

    sealed interface Intent {
        data object NextScreen : Intent
    }
}