package uz.gita.mobilebanking.presentation.transfer_verify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.data.model.PayedCardData
import uz.gita.mobilebanking.domain.use_case.SavePayedCard
import uz.gita.mobilebanking.domain.use_case.TransferResendUseCase
import uz.gita.mobilebanking.domain.use_case.TransferVerifyUseCase
import uz.gita.mobilebanking.utils.logger
import javax.inject.Inject

@HiltViewModel
class TransferVerifyModel @Inject constructor(
    private val transferVerifyDirection: TransferVerifyDirection,
    private val transferVerifyUseCase: TransferVerifyUseCase,
    private val transferResendUseCase: TransferResendUseCase,
    private val savePayedCard: SavePayedCard,
) : TransferVerifyContract.Model, ViewModel() {

    var token: String = ""
    var amount: String = ""
    var receiverName: String = ""
    var receiverPan: String = ""

    override fun onEventDispatcher(intent: TransferVerifyContract.Intent) {
        when (intent) {
            TransferVerifyContract.Intent.Back -> intent { transferVerifyDirection.back() }

            is TransferVerifyContract.Intent.SavePhoneNumber ->
                intent { reduce { TransferVerifyContract.UIState(phoneNumber = intent.phoneNumber) } }


            is TransferVerifyContract.Intent.SaveToken ->
                intent { reduce { TransferVerifyContract.UIState(token = intent.token) } }

            is TransferVerifyContract.Intent.ResendSms -> {
                transferResendUseCase(intent.token).onEach {
                    it.onSuccess { token ->
                        intent {
                            this@TransferVerifyModel.token = token
                            reduce {
                                TransferVerifyContract.UIState(
                                    phoneNumber = this.state.phoneNumber,
                                    token = token
                                )
                            }
                        }
                    }
                    it.onFailure { logger("transferResendUseCase.onFailure") }
                }.launchIn(viewModelScope)
            }

            is TransferVerifyContract.Intent.CheckUserCode -> {
                transferVerifyUseCase(intent.token, intent.verificationCode)
                    .onEach {
                        it.onSuccess {
                            logger("to success screen")
                            viewModelScope.launch(Dispatchers.IO) {
                                savePayedCard(PayedCardData(receiverPan, this@TransferVerifyModel.receiverName))
                            }
                            transferVerifyDirection.toSuccessScreen(amount, receiverName, receiverPan)
                        }
                        it.onFailure { logger("transferVerifyUseCase.onFailure") }
                    }.launchIn(viewModelScope)
            }
        }
    }

    override fun saveData(token: String, amount: String, receiverName: String, receiverPan: String) {
        this.token = token
        this.amount = amount
        this.receiverName = receiverName
        this.receiverPan = receiverPan
    }

    override val container = container<TransferVerifyContract.UIState, TransferVerifyContract.SideEffect>(
        TransferVerifyContract.UIState()
    )
}
