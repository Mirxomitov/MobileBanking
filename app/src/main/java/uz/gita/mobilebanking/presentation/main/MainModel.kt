package uz.gita.mobilebanking.presentation.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.domain.RegistrationRepository
import javax.inject.Inject

@HiltViewModel
class MainModel @Inject constructor(
    private val repository: RegistrationRepository,
    private val direction: MainDirection
) : MainContract.Model, ViewModel() {
    override fun onEventDispatcher(intent: MainContract.Intent) = intent {
        when (intent) {
            is MainContract.Intent.OpenProfileScreen -> {
                direction.toProfileScreen()
            }
        }
    }

    override val container = container<MainContract.UIState, MainContract.SideEffect>(MainContract.UIState.InitState) {}
}