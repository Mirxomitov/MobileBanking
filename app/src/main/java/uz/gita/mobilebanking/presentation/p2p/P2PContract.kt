package uz.gita.mobilebanking.presentation.p2p

import org.orbitmvi.orbit.ContainerHost
import uz.gita.mobilebanking.data.model.ui.CardData

interface P2PContract {

    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val cards: List<CardData> = emptyList()
    )

    sealed interface SideEffect
    sealed interface Intent {
        data object Back : Intent
        data class Pay(
            val senderId : String,
            val receiverPan : String,
            val amount : Int,
        ) : Intent
    }
}