package uz.gita.mobilebanking.presentation.transfer_success

import org.orbitmvi.orbit.ContainerHost

sealed interface TransferConfirmationContract {

    sealed interface Model : ContainerHost<SideEffect, UIState> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface Intent {
        data object Ready : Intent
    }

    sealed interface UIState {
        data object Init : SideEffect

    }

    sealed interface SideEffect {

    }
}