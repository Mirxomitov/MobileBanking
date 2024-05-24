package uz.gita.mobilebanking.presentation.transfer_verify

import org.orbitmvi.orbit.ContainerHost

interface TransferVerifyContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
        fun saveData(
            token: String,
            amount: String,
            receiverName: String,
            receiverPan: String,
        )
    }

    data class UIState(
        val phoneNumber: String = "",
        val token : String = "",

        )

    sealed interface SideEffect
    sealed interface Intent {
        data class CheckUserCode(val token: String, val verificationCode: String) : Intent
        data class ResendSms(val token: String) : Intent
        data object Back : Intent
        data class SaveToken(val token : String,) : Intent
        data class SavePhoneNumber(val phoneNumber: String) : Intent
    }
}