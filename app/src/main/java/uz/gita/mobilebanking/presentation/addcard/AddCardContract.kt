package uz.gita.mobilebanking.presentation.addcard

import org.orbitmvi.orbit.ContainerHost

interface AddCardContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatchers(intent: Intent)
    }

    data object UIState
    data object SideEffect
    sealed interface Intent {
        data object ToScanCardScreen : Intent
        data object Back : Intent
    }
}