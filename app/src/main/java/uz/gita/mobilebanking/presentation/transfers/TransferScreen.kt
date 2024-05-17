package uz.gita.mobilebanking.presentation.transfers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.presentation.transfers.components.DefaultState
import uz.gita.mobilebanking.presentation.transfers.components.SearchBar
import uz.gita.mobilebanking.presentation.transfers.components.TopBarTransfer
import uz.gita.mobilebanking.ui.theme.CardColor
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.utils.previewStateOf

class TransfersScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: TransferContract.Model = getViewModel<TransferModel>()

        TransactionsScreenContent(
            viewModel.collectAsState(),
            viewModel::onEventDispatchers,
        )
    }
}

@Composable
private fun TransactionsScreenContent(
    uiState: State<TransferContract.UIState>, onEventDispatchers: (TransferContract.Intent) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var isSearchingStateActive by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var isSearchBarFocused by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopBarTransfer(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            visible = isSearchingStateActive,
            onClick = {
                isSearchingStateActive = false
                searchText = ""
                focusManager.clearFocus(true)
            })
    }) {
        Column(
            modifier = Modifier
                .background(color = CardColor)
                .verticalScroll(rememberScrollState())
                .padding(it),
        ) {
            SearchBar(modifier = Modifier.padding(horizontal = 12.dp),
                onClickContacts = {},
                onClickScan = {},
                onValueChange = { searchText = it },
                focusRequester = focusRequester,
                onFocusChanged = {
                    isSearchBarFocused = it.isFocused
                    if (isSearchBarFocused) isSearchingStateActive = true
                })

            if (isSearchingStateActive) {
                OnSearchState()
            } else {
                DefaultState(onClickLastPayedCard = { onEventDispatchers(TransferContract.Intent.ToP2PScreen) })
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_7_pro")
@Composable
fun TransactionsScreenPreview() {
    MobileBankingTheme {
        TransactionsScreenContent(previewStateOf(value = TransferContract.UIState), {})
    }
}

@Composable
fun OnSearchState() {}