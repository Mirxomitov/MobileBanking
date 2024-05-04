package uz.gita.mobilebanking.presentation.verify

import org.orbitmvi.orbit.ContainerHost

interface VerifyContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        data object InitState : UIState
    }

    sealed interface SideEffect
    sealed interface Intent {
        data class CheckUserCode(val token : String, val verificationCode: String, val isSignIn : Boolean) : Intent
        data object ToCreatePinScreen : Intent
    }
}