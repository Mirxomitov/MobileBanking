package uz.gita.mobilebanking.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.model.response.auth.SignUpResponse
import uz.gita.mobilebanking.domain.RegistrationRepository
import uz.gita.mobilebanking.utils.logger
import javax.inject.Inject

@HiltViewModel
class AuthModel @Inject constructor(
    private val direction: AuthDirection,
    private val repository: RegistrationRepository
) : ViewModel(), AuthContract.Model {

    override fun onEventDispatcher(intent: AuthContract.Intent) = intent {
        when (intent) {
            is AuthContract.Intent.SignIn -> {
                repository.phoneNumber(intent.phoneNumber)

                repository.signIn(intent.phoneNumber)
                    .onEach {
                        it.onSuccess {
                            direction.toVerifyScreen(it.token, true)
                        }
                        it.onFailure {
                            logger("AuthModel -> sign in failure ${it.message}")
                            signUp(
                                phone = intent.phoneNumber,
                                onSuccess = {
                                    intent { direction.toVerifyScreen(it.token, false) }
                                }
                            )
                        }
                    }.launchIn(viewModelScope)
            }

            is AuthContract.Intent.ChangeLanguageDialog -> {
                postSideEffect(AuthContract.SideEffect.LanguageDialog(true))
            }

            is AuthContract.Intent.ChangeLanguage -> {
                repository.saveActiveLanguage(intent.isUzbekLanguage)
            }

            is AuthContract.Intent.GetLanguage -> {}

            is AuthContract.Intent.OnClickOffer -> {
                postSideEffect(AuthContract.SideEffect.OpenOffer)
            }
        }
    }

    override val container = container<AuthContract.UIState, AuthContract.SideEffect>(
        AuthContract.UIState("Uzbek", R.drawable.ic_flag_uz)
    )

    private fun signUp(phone: String, onSuccess: (SignUpResponse) -> Unit) {
        repository.signUp(phone)
            .onEach {
                it.onSuccess { signUpResponse ->
                    logger("signUp succcess")
                    onSuccess(signUpResponse)
                }
                it.onFailure {
                    logger("AuthModel -> sign UP failure ${it.message}")
                }
            }.launchIn(viewModelScope)
    }
}