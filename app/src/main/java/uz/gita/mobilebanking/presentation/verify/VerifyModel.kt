package uz.gita.mobilebanking.presentation.verify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.RegistrationRepository
import uz.gita.mobilebanking.utils.logger
import javax.inject.Inject

@HiltViewModel
class VerifyModel @Inject constructor(
    private val verifyDirection: VerifyDirection,
    private val repository: RegistrationRepository
) : VerifyContract.Model, ViewModel() {
    override fun onEventDispatcher(intent: VerifyContract.Intent) {
        when (intent) {
            is VerifyContract.Intent.ToCreatePinScreen -> {
                intent { verifyDirection.toPinCreateScreen() }
            }

            is VerifyContract.Intent.CheckUserCode -> {
                if (intent.isSignIn) {
                    repository.signInVerify(intent.token, intent.verificationCode)
                        .onEach {
                            it.onSuccess {
                                intent { verifyDirection.toPinCreateScreen() }
                                logger("signInVerify success")
                            }
                            it.onFailure {
                                logger("signInVerify failure ${it.message}")
                            }
                        }.launchIn(viewModelScope)
                } else {
                    repository.signUpVerify(intent.token, intent.verificationCode)
                        .onEach {
                            it.onSuccess {
                                intent { verifyDirection.toPinCreateScreen() }
                                logger("signUpVerify success")
                            }
                            it.onFailure {
                                logger("signUpVerify failure ${it.message}")
                            }
                        }.launchIn(viewModelScope)
                }
            }
        }
    }

    override val container =
        container<VerifyContract.UIState, VerifyContract.SideEffect>(VerifyContract.UIState.InitState) {

        }
}