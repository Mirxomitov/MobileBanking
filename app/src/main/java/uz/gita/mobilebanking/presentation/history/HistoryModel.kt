package uz.gita.mobilebanking.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.use_case.TransferGetHistoryUseCase
import javax.inject.Inject

@HiltViewModel
class HistoryModel @Inject constructor(
    private val direction: HistoryDirection,
    private val transferGetHistoryUseCase: TransferGetHistoryUseCase
) : ViewModel(), HistoryContract.Model {


    override fun onEventDispatcher(intent: HistoryContract.Intent) {

    }

    override fun getHistory() =
        transferGetHistoryUseCase(10, 1).cachedIn(viewModelScope)


    override val container =
        container<HistoryContract.UIState, HistoryContract.SideEffect>(HistoryContract.UIState)
}