package uz.gita.mobilebanking.presentation.main_navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.presentation.hisotory.HistoryScreen
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
            val icon = painterResource(id = R.drawable.history)

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
        HistoryScreen().Content()
    }
}