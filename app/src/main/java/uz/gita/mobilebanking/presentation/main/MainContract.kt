package uz.gita.mobilebanking.presentation.main

import org.orbitmvi.orbit.ContainerHost
import uz.gita.mobilebanking.data.model.ui.CardData

interface MainContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val cards: List<CardData> = listOf(CardData("", "", "", ""), CardData("", "", "", ""))
    )

    sealed interface SideEffect {}
    sealed interface Intent {
        data object OpenProfileScreen : Intent
        data object OpenAddCardScreen : Intent
    }
}