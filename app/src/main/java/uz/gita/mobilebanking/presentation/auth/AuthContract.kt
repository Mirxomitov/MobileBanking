package uz.gita.mobilebanking.presentation.auth

import androidx.annotation.DrawableRes
import org.orbitmvi.orbit.ContainerHost

interface AuthContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState (
        val language: String,
        @DrawableRes val icon : Int
    )

    sealed interface SideEffect {
        data class LanguageDialog(val isCurrentUzbek: Boolean) : SideEffect
    }

    interface Intent {
        data object ChangeLanguageDialog : Intent
        data object GetLanguage : Intent
        data class SignIn(val phoneNumber: String) : Intent
        data class ChangeLanguage(val isUzbekLanguage: Boolean) : Intent
    }
}