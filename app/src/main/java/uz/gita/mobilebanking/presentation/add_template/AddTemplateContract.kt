package uz.gita.mobilebanking.presentation.add_template

import org.orbitmvi.orbit.ContainerHost
import uz.gita.mobilebanking.data.model.PayedCardData

interface AddTemplateContract {
    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        data class ChooseCardTemplate(val lastPayedCards: List<PayedCardData> = listOf()) : UIState
        data class AddTemplateName(val card: PayedCardData = PayedCardData("", "")) : UIState
    }

    data object SideEffect

    sealed interface Intent {
        data class AddTemplate(val card: PayedCardData) : Intent
        data object Back : Intent
        data object ChangeUIToCardsState :  Intent
    }
}