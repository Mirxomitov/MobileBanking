package uz.gita.mobilebanking.presentation.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.presentation.main.MainScreen
import uz.gita.mobilebanking.presentation.transactions.TransfersScreen

object MainTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.main)
            val icon = rememberVectorPainter(Icons.Default.Home)

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
            val icon = rememberVectorPainter(Icons.Default.Home)

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
            val title = stringResource(R.string.main)
            val icon = rememberVectorPainter(Icons.Default.Home)

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {

            Text(text = "Payment")
        }

    }
}

object HistoryTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.main)
            val icon = rememberVectorPainter(Icons.Default.Home)

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