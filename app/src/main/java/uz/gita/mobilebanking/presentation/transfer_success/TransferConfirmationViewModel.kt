package uz.gita.mobilebanking.presentation.transfer_success

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.presentation.main_navigation.MainNavigationScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject

@HiltViewModel
class TransferConfirmationViewModel @Inject constructor(
    private val navigator: AppNavigator
) : TransferConfirmationContract.Model, ViewModel() {

    override val container: Container<TransferConfirmationContract.SideEffect, TransferConfirmationContract.UIState> =
        container(TransferConfirmationContract.UIState.Init)

    override fun onEventDispatcher(intent: TransferConfirmationContract.Intent) = intent {
        when (intent) {
            TransferConfirmationContract.Intent.Ready -> {
                navigator.replaceAll(MainNavigationScreen())
            }
        }
    }
}