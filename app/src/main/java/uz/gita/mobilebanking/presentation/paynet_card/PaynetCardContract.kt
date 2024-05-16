package uz.gita.mobilebanking.presentation.paynet_card

import org.orbitmvi.orbit.ContainerHost

interface PaynetCardContract {
    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data object UIState
    data object SideEffect
    sealed interface Intent {
        data object Back : Intent
    }
}