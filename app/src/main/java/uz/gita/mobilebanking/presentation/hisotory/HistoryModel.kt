package uz.gita.mobilebanking.presentation.hisotory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.use_case.TransferGetHistoryUseCase
import uz.gita.mobilebanking.utils.logger
import javax.inject.Inject

@HiltViewModel
class HistoryModel @Inject constructor(
    private val direction: HistoryDirection,
    private val transferGetHistoryUseCase: TransferGetHistoryUseCase
) : ViewModel(), HistoryContract.Model {

    override fun onEventDispatcher(intent: HistoryContract.Intent) {
        when (intent) {
            HistoryContract.Intent.GetHistory -> {
                viewModelScope.launch {
                    transferGetHistoryUseCase(10, 1)
                        .cachedIn(viewModelScope)
                        .collect { pagingData ->
                            intent {
                                reduce { HistoryContract.UIState.Content(transferHistoryResponse = flowOf(pagingData)) }
                            }
                            logger("HistoryViewModel.onEventDispatcher.GetHistory.collect${pagingData}")
                        }
                }
            }
        }
    }

    override val container =
        container<HistoryContract.UIState, HistoryContract.SideEffect>(HistoryContract.UIState.IsLoading)
}