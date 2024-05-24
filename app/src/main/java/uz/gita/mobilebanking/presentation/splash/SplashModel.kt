package uz.gita.mobilebanking.presentation.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.repositories.RegistrationRepository
import javax.inject.Inject

@HiltViewModel
class SplashModel @Inject constructor(
    private val direction: SplashDirection,
    private val registrationRepository: RegistrationRepository,
) : ViewModel(), SplashContract.Model {
    override fun onEventDispatcher(intent: SplashContract.Intent) {
        when (intent) {
            is SplashContract.Intent.NextScreen -> {
                intent {
                    when (registrationRepository.signed()) {
                        true -> {
                            direction.toPinScreen()
                        }

                        false -> {
                            direction.toAuthScreen()
                        }
                    }
                }
            }
        }
    }

    override val container =
        container<SplashContract.UIState, SplashContract.SideEffect>(SplashContract.UIState.InitState)
}