package uz.gita.mobilebanking.presentation.hisotory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.data.toHistoryChild
import uz.gita.mobilebanking.presentation.hisotory.components.HistoryListItem
import uz.gita.mobilebanking.presentation.hisotory.components.HistoryTopBar
import uz.gita.mobilebanking.ui.theme.MainBgLight
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.utils.logger
import uz.gita.mobilebanking.utils.previewStateOf

class HistoryScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: HistoryContract.Model = getViewModel<HistoryModel>()


        HistoryContent(
            uiState = viewModel.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher,
            )
    }
}
@Composable
fun HistoryContent(
    uiState: State<HistoryContract.UIState>,
    onEventDispatcher: (HistoryContract.Intent) -> Unit,
) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(MainBgLight),
        topBar = { HistoryTopBar {} }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = uiState.value) {
                is HistoryContract.UIState.IsLoading -> {
                    Column(modifier = Modifier.align(Alignment.Center)) {
                        CircularProgressIndicator()
                        Button(onClick = { onEventDispatcher(HistoryContract.Intent.GetHistory) }) {
                            Text(text = "GET HISTORY")
                        }
                    }
                }

                is HistoryContract.UIState.Error -> {
                    Button(onClick = { onEventDispatcher(HistoryContract.Intent.GetHistory) }) {
                        Text(text = "GET HISTORY WHEN ERROR")
                    }
                }

                is HistoryContract.UIState.Content -> {
                    "${state.transferHistoryResponse.collectAsLazyPagingItems().itemSnapshotList}".logger()
                    val pagingData = state.transferHistoryResponse.collectAsLazyPagingItems()

                    LazyColumn() {
                        items(items = pagingData.itemSnapshotList) { item ->
                            item?.let {
                                LazyColumn() {
                                    items(items = item.child) { childItem ->
                                        HistoryListItem(transferHistoryChild = childItem.toHistoryChild())
                                    }
                                }
                            }
                        }
                    }


                    Button(onClick = { onEventDispatcher(HistoryContract.Intent.GetHistory) }) {
                        Text(text = "GET NEW HISTORY")
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun HistoryPreview() {
    MobileBankingTheme {
        HistoryContent(
//            previewStateOf(
//                value = HistoryContract.UIState.Content(
//                    transferHistoryData = TransferHistoryData(
//                        child = arrayListOf<TransferHistoryChildData>().also {
//                            for (i in 0..25) {
//                                it.add(TransferHistoryChildData(i, "Tohir$i", 100000000, "Mirxomitov$i", "income"))
//                            }
//                        }, currentPage = 1, totalElements = 25, totalPages = 3
//                    )
//                )
//            ),
            previewStateOf(value = HistoryContract.UIState.IsLoading),
            {})
    }
}