package uz.gita.mobilebanking.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.RegistrationRepository
import uz.gita.mobilebanking.domain.use_case.HomeGetFullInfoUseCase
import uz.gita.mobilebanking.utils.toLog
import javax.inject.Inject

@HiltViewModel
class ProfileModel @Inject constructor(
    private val direction: ProfileDirection,
    private val getFullInfoUseCase: HomeGetFullInfoUseCase,
    private val registrationRepository: RegistrationRepository,
) : ViewModel(), ProfileContract.Model {

    init {
        getFullInfo()
    }

    override fun onEventDispatchers(intent: ProfileContract.Intent) {
        when (intent) {
            ProfileContract.Intent.Back -> {
                intent {
                    direction.back()
                }
            }

            ProfileContract.Intent.ToMaps -> intent { direction.toMapScreen() }
            is ProfileContract.Intent.LogOut -> {
                registrationRepository.signOut()
                    .onEach {
                        it.onSuccess {
                            toLog("ProfileContract.Intent.LogOut.success ${it.message}")
                            direction.toAuthScreen()
                        }
                        it.onFailure {
                            toLog("ProfileContract.Intent.LogOut.failure ${it.message}")
                            direction.toAuthScreen()
                        }
                    }
                    .launchIn(viewModelScope)
            }
        }
    }

    override val container =
        container<ProfileContract.UIState, ProfileContract.SideEffect>(ProfileContract.UIState()) {}

    private fun getFullInfo() {
        getFullInfoUseCase().onEach {
            it.onSuccess {
                intent { reduce { ProfileContract.UIState(it) } }
            }
        }.launchIn(viewModelScope)
    }
}