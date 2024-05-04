package uz.gita.mobilebanking.presentation.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ProfileModel @Inject constructor(
    private val direction: ProfileDirection
) : ViewModel(), ProfileContract.Model {
    override fun onEventDispatchers(intent: ProfileContract.Intent) {
        when (intent) {
            ProfileContract.Intent.Back -> {
                intent {
                    direction.back()
                }
            }
        }

    }

    override val container =
        container<ProfileContract.UIState, ProfileContract.SideEffect>(ProfileContract.UIState.InitState) {}
}