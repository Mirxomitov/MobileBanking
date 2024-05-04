package uz.gita.mobilebanking.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.mobilebanking.presentation.main.components.CashBack
import uz.gita.mobilebanking.presentation.main.components.Exchange
import uz.gita.mobilebanking.presentation.main.components.FillTransactPay
import uz.gita.mobilebanking.presentation.main.components.MainTop
import uz.gita.mobilebanking.presentation.main.components.MyCards
import uz.gita.mobilebanking.presentation.main.components.PaynetAvia
import uz.gita.mobilebanking.presentation.main.components.UserBalanceWithEye
import uz.gita.mobilebanking.ui.theme.mainBgLight

class MainScreen : Screen {
    @Composable
    override fun Content() {
        MainContent()
    }
}

@Composable
private fun MainContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mainBgLight)
            .padding(horizontal = 12.dp)
    ) {
        MainTop(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            userName = "Tohir",
            onClickItem = {}
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            UserBalanceWithEye(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth(),
                balance = "192 891"
            )

            Spacer(modifier = Modifier.height(12.dp))

            FillTransactPay(
                modifier = Modifier
                    .fillMaxWidth(),
                balance = "0",
                cardNumber = "3600",
                onClickWhatIsIt = { },
                onClickItem = { },
                onClickFill = { },
                onClickTransact = { },
                onClickPay = { }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Exchange(
                modifier = Modifier.fillMaxWidth(),
                onClickItem = {},
                onClickExchange = {}
            )

            PaynetAvia(Modifier.padding(top = 12.dp))

            Row(
                Modifier.padding(top = 12.dp)
            ) {

                Spacer(modifier = Modifier.padding(12.dp))
                CashBack(Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(200.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainContent()
}