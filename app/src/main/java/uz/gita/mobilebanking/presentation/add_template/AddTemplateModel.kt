package uz.gita.mobilebanking.presentation.add_template

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.data.model.PayedCardData
import uz.gita.mobilebanking.domain.use_case.GetPayedCardsUseCase
import uz.gita.mobilebanking.utils.logger
import javax.inject.Inject

@HiltViewModel
class AddTemplateModel @Inject constructor(
    private val direction: AddTemplateDirection,
    private val lastPayedCardsUseCase: GetPayedCardsUseCase,
) : AddTemplateContract.Model, ViewModel() {

    @Stable
    private var ls = listOf<PayedCardData>()

    init {
        viewModelScope.launch {
            ls = withContext(Dispatchers.IO) {
                lastPayedCardsUseCase()
            }

            intent { reduce { AddTemplateContract.UIState.ChooseCardTemplate(ls) } }
        }
    }

    override fun onEventDispatcher(intent: AddTemplateContract.Intent) = intent {
        when (intent) {
            AddTemplateContract.Intent.Back -> direction.back()
            is AddTemplateContract.Intent.AddTemplate -> {
                reduce { AddTemplateContract.UIState.AddTemplateName(intent.card) }
            }

            is AddTemplateContract.Intent.ChangeUIToCardsState -> {
                reduce { AddTemplateContract.UIState.ChooseCardTemplate(ls) }
            }
        }
    }


    override val container =
        container<AddTemplateContract.UIState, AddTemplateContract.SideEffect>(AddTemplateContract.UIState.ChooseCardTemplate())
}
