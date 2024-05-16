package uz.gita.mobilebanking.presentation.paynet_card

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class PaynetCardModel @Inject constructor(
    private val paynetCardDirection: PaynetCardDirection
) : ViewModel(), PaynetCardContract.Model {
    override fun onEventDispatcher(intent: PaynetCardContract.Intent) {
        when (intent) {
            PaynetCardContract.Intent.Back -> intent { paynetCardDirection.back() }
        }
    }

    override val container =
        container<PaynetCardContract.UIState, PaynetCardContract.SideEffect>(PaynetCardContract.UIState)

}