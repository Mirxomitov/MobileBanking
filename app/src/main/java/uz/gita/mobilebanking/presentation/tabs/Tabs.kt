package uz.gita.mobilebanking.presentation.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.presentation.main.MainScreen
import uz.gita.mobilebanking.presentation.payments.PaymentsScreen
import uz.gita.mobilebanking.presentation.transfers.TransfersScreen

object MainTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.main)
            val icon = painterResource(id = R.drawable.home)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        MainScreen().Content()
    }
}

object TransfersTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.transfers)
            val icon = painterResource(id = R.drawable.ic_action_transfers)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        TransfersScreen().Content()
    }
}

object PaymentTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.payment)
            val icon = painterResource(id = R.drawable.wallet)

            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        PaymentsScreen().Content()
    }
}

object HistoryTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.history)
            val icon = painterResource(id = R.drawable.home)

            return remember {
                TabOptions(
                    index = 3u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "History")
        }
    }
}