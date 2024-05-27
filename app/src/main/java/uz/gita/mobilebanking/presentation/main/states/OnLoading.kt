package uz.gita.mobilebanking.presentation.main.states

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.gita.mobilebanking.presentation.main.MainContract
import uz.gita.mobilebanking.presentation.main.components.CashBack
import uz.gita.mobilebanking.presentation.main.components.Exchange
import uz.gita.mobilebanking.presentation.main.components.FillTransactPayShimmer
import uz.gita.mobilebanking.presentation.main.components.MyCardsShimmer
import uz.gita.mobilebanking.presentation.main.components.MyDebt
import uz.gita.mobilebanking.presentation.main.components.MyHome
import uz.gita.mobilebanking.presentation.main.components.PaynetAvia
import uz.gita.mobilebanking.presentation.main.components.UserBalanceWithEyeShimmer
import uz.gita.mobilebanking.ui.theme.MainBgLight
import uz.gita.mobilebanking.utils.Constants

@Composable
fun OnLoading(
    paddingValues: PaddingValues,
    uiState: State<MainContract.UIState>,
    onEventDispatcher: (MainContract.Intent) -> Unit
) {
    val isVisibleMoney by remember { mutableStateOf(true) }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(MainBgLight)
            .padding(paddingValues)
    ) {
        item {
            UserBalanceWithEyeShimmer(
                modifier = Modifier
                    .padding(12.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            FillTransactPayShimmer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp),
                onClickWhatIsIt = {
                    onEventDispatcher(MainContract.Intent.OpenWhatIsIt)
                },
                onClickItem = { },
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
                onClickExchange = {})
        }

        item {
            Row(
                Modifier
                    .padding(top = 12.dp)
                    .padding(horizontal = 12.dp)
                    .height(220.dp)
            ) {
                MyCardsShimmer(
                    modifier = Modifier
                        .weight(1f)
                        .height(220.dp),
                )

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

            Spacer(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .height(Constants.BOTTOM_NAVIGATION_HEIGHT.dp)
            )
        }
    }
}