package uz.gita.mobilebanking.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SplashModel @Inject constructor(
    private val direction: SplashDirection
) : ViewModel(), SplashContract.Model {
    override fun onEventDispatcher(intent: SplashContract.Intent) {
        when (intent) {
            is SplashContract.Intent.NextScreen -> {
                viewModelScope.launch {
                    direction.toNext()
                }
            }
        }
    }

    override val container =
        container<SplashContract.UIState, SplashContract.SideEffect>(SplashContract.UIState.InitState)
}