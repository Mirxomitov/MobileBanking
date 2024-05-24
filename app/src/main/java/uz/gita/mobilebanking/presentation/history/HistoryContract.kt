package uz.gita.mobilebanking.presentation.history

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.orbitmvi.orbit.ContainerHost
import uz.gita.mobilebanking.data.model.HistoryChildData

interface HistoryContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
        fun getHistory(): Flow<PagingData<HistoryChildData>>
    }

    data object UIState

    sealed interface SideEffect
    sealed interface Intent {
        data object GetHistory : Intent
    }
}