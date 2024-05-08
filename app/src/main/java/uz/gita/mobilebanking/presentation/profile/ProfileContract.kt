package uz.gita.mobilebanking.presentation.profile

import org.orbitmvi.orbit.ContainerHost

interface ProfileContract {

    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatchers(intent : Intent)
    }

    data object UIState
    sealed interface Intent {
        data object Back : Intent
        data object ToMaps : Intent
        data object LogOut : Intent
    }
    sealed interface SideEffect
}