package uz.gita.mobilebanking.presentation.my_cards

import org.orbitmvi.orbit.ContainerHost

interface MyCardsContract {
    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data object UIState
    sealed interface SideEffect
    sealed interface Intent { data object Back : Intent }
}