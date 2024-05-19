package uz.gita.mobilebanking.presentation.transfers

import org.orbitmvi.orbit.ContainerHost

interface TransferContract {
    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatchers(intent: Intent)
    }

    data class UIState(
        var ownerName: String = "",
        val pan: String = ""
    )

    interface SideEffect
    sealed interface Intent {
        data class GetCardOwnerByCardNumber(val pan: String) : Intent
        data class ToP2PScreen(val pan: String, val ownerName: String) : Intent
        data object ClearOwnerName : Intent
    }
}