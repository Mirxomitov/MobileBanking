package uz.gita.mobilebanking.presentation.transfers

import org.orbitmvi.orbit.ContainerHost

interface TransferContract {
    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatchers(intent : Intent)
    }

    data object UIState
    sealed interface SideEffect
    sealed interface Intent {
        data object ToP2PScreen : Intent
    }
}