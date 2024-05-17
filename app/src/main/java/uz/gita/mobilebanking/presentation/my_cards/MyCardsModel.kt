package uz.gita.mobilebanking.presentation.my_cards

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

class MyCardsModel @Inject constructor(
    private val myCardsDirection: MyCardsDirection,
) : ViewModel(), MyCardsContract.Model {
    override fun onEventDispatcher(intent: MyCardsContract.Intent) {
        when (intent) {
            MyCardsContract.Intent.Back -> intent {
                myCardsDirection.back()
            }
        }
    }

    override val container = container<MyCardsContract.UIState, MyCardsContract.SideEffect>(MyCardsContract.UIState)
}