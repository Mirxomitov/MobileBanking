package uz.gita.mobilebanking.presentation.transfer_verify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.use_case.TransferVerifyUseCase
import uz.gita.mobilebanking.utils.logger
import javax.inject.Inject

@HiltViewModel
class TransferVerifyModel @Inject constructor(
    private val transferVerifyUseCase: TransferVerifyUseCase,
    private val transferVerifyDirection: TransferVerifyDirection
) : TransferVerifyContract.Model, ViewModel() {
    override fun onEventDispatcher(intent: TransferVerifyContract.Intent) {
        when (intent) {
            TransferVerifyContract.Intent.Back -> intent { transferVerifyDirection.back() }

            is TransferVerifyContract.Intent.SavePhoneNumber ->
                intent { reduce { TransferVerifyContract.UIState(phoneNumber = intent.phoneNumber) } }


            is TransferVerifyContract.Intent.SaveToken ->
                intent { reduce { TransferVerifyContract.UIState(token = intent.token) } }

            is TransferVerifyContract.Intent.ResendSms -> {

            }

            is TransferVerifyContract.Intent.CheckUserCode -> {
                transferVerifyUseCase(intent.token, intent.verificationCode)
                    .onEach {
                        it.onSuccess {
                            logger("transferVerifyUseCase.isSuccess")
                        }
                        it.onFailure {
                            logger("transferVerifyUseCase.onFailure")
                        }
                    }
                    .launchIn(viewModelScope)
            }
        }
    }

    override val container =
        container<TransferVerifyContract.UIState, TransferVerifyContract.SideEffect>(
            TransferVerifyContract.UIState("", "")
        )
}