package uz.gita.mobilebanking.presentation.hisotory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.use_case.TransferGetHistory
import uz.gita.mobilebanking.utils.logger
import javax.inject.Inject

@HiltViewModel
class HistoryModel @Inject constructor(
    private val direction: HistoryDirection,
    private val transferGetHistory: TransferGetHistory
) : ViewModel(), HistoryContract.Model {

    override fun onEventDispatcher(intent: HistoryContract.Intent) {
        when (intent) {
            HistoryContract.Intent.GetHistory -> transferGetHistory().onEach {
                logger("Get History click in model")
                it.onSuccess { logger("transferGetHistory.onSuccess $it") }
                it.onFailure { logger("transferGetHistory.onFailure ${it.message}") }
            }.launchIn(viewModelScope)
        }
    }

    override val container = container<HistoryContract.UIState, HistoryContract.SideEffect>(HistoryContract.UIState)
}