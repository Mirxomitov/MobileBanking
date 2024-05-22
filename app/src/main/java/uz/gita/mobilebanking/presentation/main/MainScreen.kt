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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.Constants
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
import uz.gita.mobilebanking.ui.theme.MainBgLight
import uz.gita.mobilebanking.utils.previewStateOf

object MainScreen : Tab {
    override val options: TabOptions
        @Composable get() {
            val title = stringResource(R.string.main)
            val icon = painterResource(id = R.drawable.home)

            return remember {
                TabOptions(
                    index = 0u, title = title, icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel: MainContract.Model = getViewModel<MainModel>()

        MainContent(
            uiState = viewModel.collectAsState(), onEventDispatcher = viewModel::onEventDispatcher
        )
    }
}

@Composable
private fun MainContent(
    uiState: State<MainContract.UIState>,
    onEventDispatcher: (MainContract.Intent) -> Unit
) {
    val context = LocalContext.current
    var isVisibleMoney by remember { mutableStateOf(true) }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            when (uiState.value) {
                is MainContract.UIState.Content -> {
                    MainTop(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(horizontal = 12.dp),
                        userName = (uiState.value as MainContract.UIState.Content).firstName,
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

                else -> {}
            }
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(MainBgLight)
                .padding(it)
        ) {
            when (uiState.value) {
                is MainContract.UIState.Content -> {
                    item {
                        UserBalanceWithEye(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            balance = if (isVisibleMoney) (uiState.value as MainContract.UIState.Content).totalBalance else "• •••",
                            onClickEye = {
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
                            onClickWhatIsIt = {
                                onEventDispatcher(MainContract.Intent.OpenWhatIsIt)
                            },
                            onClickItem = { },
                            onClickFill = { },
                            onClickTransact = { },
                            onClickPay = { },
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                    item {
                        Exchange(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                            onClickItem = {},
                            onClickExchange = {})
                    }

                    item {
                        Row(
                            Modifier
                                .padding(top = 12.dp)
                                .padding(horizontal = 12.dp)
                                .height(220.dp)
                        ) {
                            when ((uiState.value as MainContract.UIState.Content).cards.size) {
                                0 -> MyCardsEmpty(Modifier.weight(1f),
                                    onClickAddCard = { onEventDispatcher(MainContract.Intent.OpenAddCardScreen) })

                                1 -> MyCardsOneCard(
                                    modifier = Modifier.weight(1f),
                                    onClickAddCard = { onEventDispatcher(MainContract.Intent.OpenAddCardScreen) },
                                    onClickCard = { onEventDispatcher(MainContract.Intent.OpenCardDetails(it)) },
                                    card = (uiState.value as MainContract.UIState.Content).cards[0]
                                )

                                2 -> MyCardsTwoCards(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(220.dp),
                                    onClickAddCard = { onEventDispatcher(MainContract.Intent.OpenAddCardScreen) },
                                    onClickFrontCard = { onEventDispatcher(MainContract.Intent.OpenCardDetails((uiState.value as MainContract.UIState.Content).cards[0])) },
                                    onClickBackCard = { onEventDispatcher(MainContract.Intent.OpenCardDetails((uiState.value as MainContract.UIState.Content).cards[1])) },
                                    frontCard = (uiState.value as MainContract.UIState.Content).cards[0],
                                    backCard = (uiState.value as MainContract.UIState.Content).cards[1],
                                )

                                else -> MyCardsMoreCards(
                                    modifier = Modifier.weight(1f),
                                    onClickShowCards = { onEventDispatcher(MainContract.Intent.OpenMyCardsScreen) },
                                    onClickFrontCard = { onEventDispatcher(MainContract.Intent.OpenCardDetails((uiState.value as MainContract.UIState.Content).cards[0])) },
                                    onClickBackCard = { onEventDispatcher(MainContract.Intent.OpenCardDetails((uiState.value as MainContract.UIState.Content).cards[1])) },
                                    cards = (uiState.value as MainContract.UIState.Content).cards
                                )
                            }

                            Spacer(modifier = Modifier.padding(4.dp))

                            CashBack(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(220.dp), isVisibleMoney = isVisibleMoney
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

                        Spacer(
                            modifier = Modifier
                                .padding(bottom = 12.dp)
                                .height(Constants.BOTTOM_NAVIGATION_HEIGHT.dp)
                        )
                    }
                }

                else -> {}
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainContent(previewStateOf(value = MainContract.UIState.Loading), {})
}