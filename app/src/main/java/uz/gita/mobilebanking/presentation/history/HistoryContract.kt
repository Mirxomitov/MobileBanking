package uz.gita.mobilebanking.presentation.history

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.orbitmvi.orbit.ContainerHost
import uz.gita.mobilebanking.data.model.response.transfer.TransferHistoryResponse

interface HistoryContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        data object IsLoading : UIState
        data class Content(val transferHistoryResponse: Flow<PagingData<TransferHistoryResponse>>) : UIState
        data class Error(val errorMessage: String) : UIState
    }


    sealed interface SideEffect
    sealed interface Intent {
        data object GetHistory : Intent
    }
}