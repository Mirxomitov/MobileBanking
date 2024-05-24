package uz.gita.mobilebanking.presentation.payments

import org.orbitmvi.orbit.ContainerHost
import uz.gita.mobilebanking.data.model.TemplateData

interface PaymentsContract {
    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        data object Error : UIState
        data object Loading : UIState
        data class Content(
            val templates: List<TemplateData>
        ) : UIState
    }

    data object SideEffect
    sealed interface Intent
}