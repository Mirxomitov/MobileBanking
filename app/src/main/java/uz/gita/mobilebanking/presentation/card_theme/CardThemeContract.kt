package uz.gita.mobilebanking.presentation.card_theme

import org.orbitmvi.orbit.ContainerHost
import uz.gita.mobilebanking.data.model.CardData

interface CardThemeContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }
    class UIState

    sealed interface SideEffect

    sealed interface Intent {
        data class UpdateCard(
            val data: CardData
        ) : Intent
        data class BackCardDetails(
            val data: CardData
        ) : Intent
    }
}