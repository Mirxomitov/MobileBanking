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

    // this is helper token saver used for saving new tokens after resending sms successfully
    private var mToken = ""

    override fun onEventDispatcher(intent: VerifyContract.Intent) {
        when (intent) {
            is VerifyContract.Intent.ShowUserPhone -> {
                container.stateFlow.value.phoneNumber = repository.phoneNumber()
            }

            is VerifyContract.Intent.ToCreatePinScreen -> {
                intent { verifyDirection.toPinCreateScreen() }
            }

            is VerifyContract.Intent.Back -> {
                logger("back pressed")
                intent { verifyDirection.back() }
            }

            is VerifyContract.Intent.CheckUserCode -> {
                if (mToken.isEmpty()) {
                    mToken = intent.token
                }

                when (intent.isSignIn) {
                    // verify sign in
                    true -> {
                        logger("Verify Sign In ${intent.verificationCode}")
                        repository.signInVerify(mToken, intent.verificationCode)
                            .onEach {
                                it.onSuccess {
                                    intent { verifyDirection.toPinCreateScreen() }
                                    logger("signInVerify success")
                                }
                                it.onFailure {
                                    logger("signInVerify failure ${it.message}")
                                }
                            }.launchIn(viewModelScope)
                    }

                    false -> {
                        // verify sign up
                        logger("Verify Sign Up ${intent.verificationCode}")
                        repository.signUpVerify(mToken, intent.verificationCode)
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


            is VerifyContract.Intent.ResendSms -> {
                when (intent.isSignIn) {
                    true -> {
                        logger("VerifyContract.Intent.ResendSms.SignIn")
                        repository.signInResend(mToken)
                            .onEach {
                                it.onSuccess {
                                    logger("sign in resend onSuccess")
                                    mToken = it.token
                                }
                                it.onFailure {
                                    logger("sign in resend onFailure ${it.message}")
                                }
                            }
                            .launchIn(viewModelScope)
                    }

                    false -> {
                        logger("VerifyContract.Intent.ResendSms.SignUp")
                        repository.signUpResend(mToken)
                            .onEach {
                                it.onSuccess {
                                    mToken = it.token
                                    logger("sign up resend onSuccess")
                                }
                                it.onFailure {
                                    logger("sign up resend onFailure ${it.message}")
                                }
                            }.launchIn(viewModelScope)
                    }
                }
            }
        }
    }

    override val container =
        container<VerifyContract.UIState, VerifyContract.SideEffect>(
            VerifyContract.UIState("")
        )
}