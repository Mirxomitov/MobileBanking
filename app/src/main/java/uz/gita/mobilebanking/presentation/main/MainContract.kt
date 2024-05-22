package uz.gita.mobilebanking.presentation.main

import org.orbitmvi.orbit.ContainerHost
import uz.gita.mobilebanking.data.model.ui.CardData

interface MainContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        data class Content(
            var cards: List<CardData>,
            val totalBalance: String,
            val firstName: String,
        ) : UIState

        data object Loading : UIState
        data class Error(val message: String) : UIState
    }

    sealed interface SideEffect
    sealed interface Intent {
        data class OpenCardDetails(val cardData: CardData) : Intent

        data object OpenProfileScreen : Intent
        data object OpenAddCardScreen : Intent
        data object OpenWhatIsIt : Intent
        data object OpenMyCardsScreen : Intent
    }
}