package uz.gita.mobilebanking.presentation.transactions

import org.orbitmvi.orbit.ContainerHost

interface TransferContract {
    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatchers(intent : Intent)
    }

    sealed interface UIState {
        data object InitState : UIState
    }
    sealed interface SideEffect
    sealed interface Intent {
        data object ToP2PScreen : Intent
    }

}