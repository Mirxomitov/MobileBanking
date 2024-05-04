package uz.gita.mobilebanking.presentation.pin_create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CreatePinModel @Inject constructor(
    private val direction: CreatePinDirection
) : ViewModel(), CreatePinContract.Model {
    override fun onEventDispatcher(intent: CreatePinContract.Intent) {
        when (intent) {
            CreatePinContract.Intent.ToPinCheckScreen -> {
                viewModelScope.launch {
                    direction.toCheckPinScreen()
                }
            }
        }
    }

    override val container =
        container<CreatePinContract.UIState, CreatePinContract.SideEffect>(CreatePinContract.UIState.InitState) {

        }
}