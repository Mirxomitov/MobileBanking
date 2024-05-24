package uz.gita.mobilebanking.presentation.payments

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class PaymentsModel @Inject constructor(

) : ViewModel(), PaymentsContract.Model {
    override fun onEventDispatcher(intent: PaymentsContract.Intent) {

    }

    override val container =
        container<PaymentsContract.UIState, PaymentsContract.SideEffect>(PaymentsContract.UIState.Loading)

}