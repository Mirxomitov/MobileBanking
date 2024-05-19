package uz.gita.mobilebanking.presentation.hisotory

import androidx.annotation.DrawableRes
import org.orbitmvi.orbit.ContainerHost

interface HistoryContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data object UIState
    sealed interface SideEffect
    interface Intent
}