package uz.gita.mobilebanking.presentation.my_cards

import org.orbitmvi.orbit.ContainerHost
import uz.gita.mobilebanking.data.model.ui.CardData

interface MyCardsContract {
    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val cards: List<CardData> = emptyList()
    )

    sealed interface SideEffect {
        data object ShowAddCardDialog : SideEffect
    }
    sealed interface Intent {
        data object Back : Intent
        data object AddCard : Intent
        data class InitCards(val listOfCards : List<CardData>) : Intent
    }
}