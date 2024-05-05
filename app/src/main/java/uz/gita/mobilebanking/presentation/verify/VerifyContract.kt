package uz.gita.mobilebanking.presentation.verify

import org.orbitmvi.orbit.ContainerHost

interface VerifyContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        var phoneNumber: String,
    )

    sealed interface SideEffect
    sealed interface Intent {
        data object ShowUserPhone : Intent
        data class CheckUserCode(val token: String, val verificationCode: String, val isSignIn: Boolean) : Intent
        data class ResendSms(val token: String, val isSignIn: Boolean) : Intent
        data object ToCreatePinScreen : Intent
        data object Back : Intent
    }
}