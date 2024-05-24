package uz.gita.mobilebanking.presentation.transfers

import org.orbitmvi.orbit.ContainerHost
import uz.gita.mobilebanking.data.model.PayedCardData
import uz.gita.mobilebanking.data.model.TemplateCardData

interface TransferContract {
    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatchers(intent: Intent)
        fun initCardNumber(cardNumber: String)
    }

    data class UIState(
        var ownerName: String = "",
        val pan: String = "",
        val cardNumber: String = "",
        val payedCards: List<PayedCardData> = listOf(),//= listOf(PayedCardData("1234567890123456", "TEST OWNER UI"))
        val templateCards: List<TemplateCardData> = listOf()
    )

    interface SideEffect
    sealed interface Intent {
        data object GetPayedCards : Intent
        data object GetTemplateCards : Intent
        data class GetCardOwnerByCardNumber(val pan: String) : Intent
        data class ToP2PScreen(val pan: String, val ownerName: String) : Intent
        data object ClearOwnerName : Intent
        data object AddCardTemplate : Intent
    }
}