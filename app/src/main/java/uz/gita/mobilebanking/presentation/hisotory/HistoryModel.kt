package uz.gita.mobilebanking.presentation.hisotory

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.RegistrationRepository
import javax.inject.Inject

@HiltViewModel
class HistoryModel @Inject constructor(
    private val direction: HistoryDirection,
    private val repository: RegistrationRepository
) : ViewModel(), HistoryContract.Model {

    override fun onEventDispatcher(intent: HistoryContract.Intent) {}

    override val container = container<HistoryContract.UIState, HistoryContract.SideEffect>(HistoryContract.UIState)
}