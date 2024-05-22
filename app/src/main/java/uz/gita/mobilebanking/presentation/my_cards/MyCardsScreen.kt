package uz.gita.mobilebanking.presentation.my_cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.model.ui.CardData
import uz.gita.mobilebanking.presentation.my_cards.components.ItemCard
import uz.gita.mobilebanking.presentation.my_cards.components.TopBar
import uz.gita.mobilebanking.ui.components.buttons.BoxedButton
import uz.gita.mobilebanking.ui.dialogs.AddCardDialog
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.utils.previewStateOf


data class MyCardsScreen(val listOfCards: List<CardData>) : Screen {
    @Composable
    override fun Content() {
        val viewModel: MyCardsContract.Model = getViewModel<MyCardsModel>()
        viewModel.onEventDispatcher(MyCardsContract.Intent.InitCards(listOfCards))

        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        viewModel.collectSideEffect {
            when (it) {
                MyCardsContract.SideEffect.ShowAddCardDialog -> {
                    bottomSheetNavigator.show(
                        AddCardDialog(
                            onUzbClick = {
                                viewModel.onEventDispatcher(MyCardsContract.Intent.AddCard)
                            },
                            onHide = {
                                bottomSheetNavigator.hide()
                            }
                        )
                    )
                }
            }
        }

        MyCardsContent(
            uiState = viewModel.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher
        )
    }
}

@Composable
fun MyCardsContent(
    uiState: State<MyCardsContract.UIState>,
    onEventDispatcher: (MyCardsContract.Intent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                onClickBack = { onEventDispatcher(MyCardsContract.Intent.Back) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = paddingValues.calculateTopPadding())
                    .padding(horizontal = paddingValues.calculateLeftPadding(LayoutDirection.Ltr))
                    .padding(horizontal = 8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                uiState.value.cards.forEach {
                    ItemCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        cardData = it
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            BoxedButton(
                modifier = Modifier.fillMaxWidth(),
                isEnabled = true,
                onClick = { onEventDispatcher(MyCardsContract.Intent.AddCard) },
                buttonTextID = R.string.add_card
            )
        }
    }
}

@Preview
@Composable
fun ContentPreview() {
    MobileBankingTheme {
        MyCardsContent(previewStateOf(
            MyCardsContract.UIState(
//                listOf(
//                    CardData("", "0005", "2028", "6", "Personal", "100000"),
//                    CardData("", "0004", "2028", "6", "Personal", "100000"),
//                    CardData("", "0004", "2028", "6", "Personal", "100000"),
//                    CardData("", "0004", "2028", "6", "Personal", "100000"),
//                )
            )
        ), {})
    }
}

@Preview
@Composable
fun ItemCardPreview() {
//    ItemCard(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(200.dp),
//        cardData = CardData(
//            "", "0005", "2028", "6", "Personal", "100000000"
//        )
//    )
}
