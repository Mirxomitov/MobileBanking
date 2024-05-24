package uz.gita.mobilebanking.presentation.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.model.HistoryChildData
import uz.gita.mobilebanking.presentation.history.components.HistoryIncomeTransactionItem
import uz.gita.mobilebanking.presentation.history.components.HistoryOutcomeTransactionItem
import uz.gita.mobilebanking.presentation.history.components.HistoryTopBar
import uz.gita.mobilebanking.ui.theme.MainBgLight
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.utils.Constants

object HistoryScreen : Tab {
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
        val viewModel: HistoryContract.Model = getViewModel<HistoryModel>()
        val ls = viewModel.getHistory().collectAsLazyPagingItems()

        HistoryContent(
            uiState = viewModel.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher,
            ls
        )
    }
}

@Composable
fun HistoryContent(
    uiState: State<HistoryContract.UIState>,
    onEventDispatcher: (HistoryContract.Intent) -> Unit,
    ls: LazyPagingItems<HistoryChildData>
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBgLight),
        topBar = { HistoryTopBar {} }
    ) { paddingValues ->
        Surface(
            Modifier
                .fillMaxSize()
                .background(MainBgLight)
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .background(MainBgLight)
                    .padding(bottom = 56.dp)
            ) {
                items(
                    ls.itemCount,
                    key = ls.itemKey { it.time },
                    contentType = ls.itemContentType { "contentType" }
                ) {
                    ls[it]?.let { data ->
                        if (data.type == "income") {
                            HistoryIncomeTransactionItem(
                                data = data,
                                onClickItem = {}
                            )
                        } else {
                            HistoryOutcomeTransactionItem(
                                data = data,
                                onClickItem = {}
                            )
                        }
                    }
                }

                item {
                    Spacer(
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                            .height(Constants.BOTTOM_NAVIGATION_HEIGHT.dp)
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun HistoryPreview() {
    MobileBankingTheme {
//        HistoryContent(
//            previewStateOf(value = HistoryContract.UIState),
//            {},
//        )
    }
}