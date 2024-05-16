package uz.gita.mobilebanking.presentation.main

import org.orbitmvi.orbit.ContainerHost
import uz.gita.mobilebanking.data.model.ui.CardData

interface MainContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        var cards: List<CardData> = listOf()
    )

    sealed interface SideEffect {}
    sealed interface Intent {
        data object OpenProfileScreen : Intent
        data object OpenAddCardScreen : Intent
        data object OpenWhatIsIt : Intent
        data object Init : Intent
    }
}