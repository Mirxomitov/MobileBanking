package uz.gita.mobilebanking.presentation.my_cards

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MyCardsModel @Inject constructor(
    private val myCardsDirection: MyCardsDirection,
) : ViewModel(), MyCardsContract.Model {
    override fun onEventDispatcher(intent: MyCardsContract.Intent) {
        when (intent) {
            MyCardsContract.Intent.Back -> intent { myCardsDirection.back() }
            MyCardsContract.Intent.AddCard -> intent { postSideEffect(MyCardsContract.SideEffect.ShowAddCardDialog) }
            is MyCardsContract.Intent.InitCards -> intent { reduce { MyCardsContract.UIState(intent.listOfCards) } }
            is MyCardsContract.Intent.ToCardDetailsScreen -> intent { myCardsDirection.toDetailsScreen(intent.cardData) }
        }
    }

    override val container = container<MyCardsContract.UIState, MyCardsContract.SideEffect>(MyCardsContract.UIState())
}