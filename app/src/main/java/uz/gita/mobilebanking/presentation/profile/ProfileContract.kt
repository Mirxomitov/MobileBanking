package uz.gita.mobilebanking.presentation.profile

import org.orbitmvi.orbit.ContainerHost
import uz.gita.mobilebanking.data.model.FullInfoData

interface ProfileContract {

    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatchers(intent : Intent)
    }

    data class UIState(
        val fullInfoData: FullInfoData? = null
    )
    sealed interface Intent {
        data object Back : Intent
        data object ToMaps : Intent
        data object LogOut : Intent
    }
    sealed interface SideEffect
}