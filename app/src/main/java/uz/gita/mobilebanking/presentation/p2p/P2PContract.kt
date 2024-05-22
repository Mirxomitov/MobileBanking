package uz.gita.mobilebanking.presentation.p2p

import org.orbitmvi.orbit.ContainerHost
import uz.gita.mobilebanking.data.model.ui.CardData

interface P2PContract {

    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        data class Content(
            val cards: List<CardData> = emptyList(),
            val receiverPan: String = "",
            val ownerName: String = "",
            ) : UIState
        data object Loading : UIState
        data class Error(val message :String,) : UIState
    }

    sealed interface SideEffect
    sealed interface Intent {
        data object Back : Intent
        data object AddCard : Intent
        data class Pay(
            val senderId: String,
            val receiverPan: String,
            val amount: Int,
        ) : Intent
        data class SaveReceiverData(val receiverPan: String, val ownerName: String) : Intent
    }
}