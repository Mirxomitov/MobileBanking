package uz.gita.mobilebanking.presentation.main

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.data.model.ui.CardData
import uz.gita.mobilebanking.presentation.main.components.CashBack
import uz.gita.mobilebanking.presentation.main.components.Exchange
import uz.gita.mobilebanking.presentation.main.components.FillTransactPay
import uz.gita.mobilebanking.presentation.main.components.MainTop
import uz.gita.mobilebanking.presentation.main.components.MyCardsEmpty
import uz.gita.mobilebanking.presentation.main.components.MyCardsMoreCards
import uz.gita.mobilebanking.presentation.main.components.MyCardsOneCard
import uz.gita.mobilebanking.presentation.main.components.MyCardsTwoCards
import uz.gita.mobilebanking.presentation.main.components.MyDebt
import uz.gita.mobilebanking.presentation.main.components.MyHome
import uz.gita.mobilebanking.presentation.main.components.PaynetAvia
import uz.gita.mobilebanking.presentation.main.components.UserBalanceWithEye
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.ui.theme.mainBgLight
import uz.gita.mobilebanking.utils.logger

class MainScreen : Screen {
    @Composable
    override fun Content() {
        MobileBankingTheme {
            val viewModel: MainContract.Model = getViewModel<MainModel>()
            MainContent(
                uiState = viewModel.collectAsState().value,
                onEventDispatcher = viewModel::onEventDispatcher
            )
        }
    }
}

@Composable
private fun MainContent(
    uiState: MainContract.UIState,
    onEventDispatcher: (MainContract.Intent) -> Unit
) {
    val context = LocalContext.current
    //  val bottomSheetNavigator = LocalBottomSheetNavigator.current

    Text(text = "")

    var isVisibleMoney by remember { mutableStateOf(true) }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MainTop(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 12.dp),
                userName = "Tohir",
                onClickItem = {
                    onEventDispatcher(MainContract.Intent.OpenProfileScreen)
                },
                icSupportClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://chat.paynet.uz/"))
                    startActivity(context, intent, null)
                },
                icNotificationClick = {
                    //
                },
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(mainBgLight)
                .padding(it)
        ) {
            item {
                UserBalanceWithEye(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    balance = if (isVisibleMoney) "1 192 891" else "• •••",
                    onClickEye = {
                        logger("MainScreen.changing isVisibleMoney =$isVisibleMoney")
                        isVisibleMoney = !isVisibleMoney
                    },
                    isVisible = isVisibleMoney
                )
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
            item {
                FillTransactPay(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .padding(top = 12.dp),
                    balance = "0",
                    cardNumber = "3600",
                    onClickWhatIsIt = { },
                    onClickItem = { },
                    onClickFill = { },
                    onClickTransact = { },
                    onClickPay = { }
                )
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
            item {
                Exchange(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    onClickItem = {},
                    onClickExchange = {}
                )
            }

            item {
                Row(
                    Modifier
                        .padding(top = 12.dp)
                        .padding(horizontal = 12.dp)
                        .height(220.dp)
                ) {

                    when (uiState.cards.size) {
                        0 -> MyCardsEmpty(
                            Modifier.weight(1f),
                            onClickAddCard = { onEventDispatcher(MainContract.Intent.OpenAddCardScreen) }
                        )

                        1 -> MyCardsOneCard(
                            modifier = Modifier.weight(1f),
                            onClickAddCard = { onEventDispatcher(MainContract.Intent.OpenAddCardScreen) },
                            onClickCard = { },
                            card = CardData("", "", "", "")
                        )

                        2 -> MyCardsTwoCards(
                            modifier = Modifier
                                .weight(1f)
                                .height(220.dp),
                            onClickAddCard = { onEventDispatcher(MainContract.Intent.OpenAddCardScreen) },
                            onClickFrontCard = {},
                            onClickBackCard = {},
                            frontCard = CardData("", "", "", ""),
                            backCard = CardData("", "", "", ""),
                        )

                        else -> MyCardsMoreCards(
                            modifier = Modifier.weight(1f),
                            onClickAddCard = { onEventDispatcher(MainContract.Intent.OpenAddCardScreen) },
                            onClickFrontCard = { },
                            onClickBackCard = { },
                            cards = listOf(CardData("", "", "", ""), CardData("", "", "", ""), CardData("", "", "", ""))
                        )
                    }

                    Spacer(modifier = Modifier.padding(4.dp))

                    CashBack(
                        modifier = Modifier
                            .weight(1f)
                            .height(220.dp),
                        isVisibleMoney = isVisibleMoney
                    )
                }
            }

            item {
                PaynetAvia(
                    Modifier
                        .padding(top = 12.dp)
                        .padding(horizontal = 12.dp)
                )
            }

            item {
                MyHome(
                    Modifier
                        .padding(top = 12.dp)
                        .padding(horizontal = 12.dp)
                )
            }
            item {
                MyDebt(
                    Modifier
                        .padding(top = 12.dp)
                        .padding(horizontal = 12.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainContent(MainContract.UIState(), {})
}