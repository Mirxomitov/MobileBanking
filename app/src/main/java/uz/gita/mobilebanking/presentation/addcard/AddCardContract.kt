package uz.gita.mobilebanking.presentation.addcard

import org.orbitmvi.orbit.ContainerHost

interface AddCardContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatchers(intent: Intent)
    }

    data class UIState(
        var cardNumber: String = "",
        var expirationDate: String = ""
    )

    data object SideEffect
    sealed interface Intent {
        data object ToScanCardScreen : Intent
        data object Back : Intent
        data class AddCard(val cardNumber: String, val expirationDate: String) : Intent
        data class SaveCard(val cardNumber: String, val expirationDate: String) : Intent
    }
}