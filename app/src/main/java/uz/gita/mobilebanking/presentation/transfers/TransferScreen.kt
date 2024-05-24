package uz.gita.mobilebanking.presentation.transfers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.presentation.transfers.components.CardNotFound
import uz.gita.mobilebanking.presentation.transfers.components.DefaultState
import uz.gita.mobilebanking.presentation.transfers.components.LastCards
import uz.gita.mobilebanking.presentation.transfers.components.SearchBar
import uz.gita.mobilebanking.presentation.transfers.components.TopBarTransfer
import uz.gita.mobilebanking.ui.components.cards.CardP2PWithCardNumber
import uz.gita.mobilebanking.ui.theme.CardColor
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.utils.Constants
import uz.gita.mobilebanking.utils.logger
import uz.gita.mobilebanking.utils.previewStateOf

object TransfersScreen : Tab {
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
        val viewModel: TransferContract.Model = getViewModel<TransferModel>()

        TransactionsScreenContent(
            viewModel.collectAsState(),
            viewModel::onEventDispatchers,
        )
    }
}

@Composable
private fun TransactionsScreenContent(
    uiState: State<TransferContract.UIState>,
    onEventDispatchers: (TransferContract.Intent) -> Unit,
) {
    var searchText by remember { mutableStateOf(uiState.value.cardNumber) }
    var isSearchingStateActive by remember { mutableStateOf(false) }
    var isSearchBarFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var filteredPayedCards by remember { mutableStateOf(uiState.value.payedCards) }

    Scaffold(
        topBar = {
            TopBarTransfer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                visible = isSearchingStateActive,
                onClick = {
                    isSearchingStateActive = false
                    searchText = ""
                    focusManager.clearFocus(true)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .background(color = CardColor)
                .fillMaxSize()
                .padding(it),
        ) {
            SearchBar(
                modifier = Modifier.padding(horizontal = 12.dp),
                onClickContacts = {},
                onClickScan = {},
                onValueChange = {
                    if (it.length <= 16) {
                        searchText = it
                        filteredPayedCards = uiState.value.payedCards.filter { it.pan.startsWith(searchText) }
                    }

                    if (searchText.length == 16) {
                        onEventDispatchers(TransferContract.Intent.GetCardOwnerByCardNumber(searchText))
                    } else {
                        onEventDispatchers(TransferContract.Intent.ClearOwnerName)
                    }
                },
                focusRequester = focusRequester,
                onFocusChanged = {
                    isSearchBarFocused = it.isFocused
                    if (isSearchBarFocused) isSearchingStateActive = true
                }
            )

            if (isSearchingStateActive) {
                if (uiState.value.ownerName.isNotEmpty() && uiState.value.pan.length == 16) {
                    CardP2PWithCardNumber(
                        modifier = Modifier.padding(top = 12.dp),
                        cardNumber = uiState.value.pan,
                        ownerName = uiState.value.ownerName,
                        onClickItem = {
                            logger("pan = ${uiState.value.pan}")
                            onEventDispatchers(
                                TransferContract.Intent.ToP2PScreen(
                                    pan = uiState.value.pan,
                                    ownerName = uiState.value.ownerName
                                )
                            )
                        }
                    )
                } else if (uiState.value.ownerName.isEmpty() && uiState.value.pan.length == 16) {
                    CardNotFound(modifier = Modifier.padding(12.dp))
                } else {
                    logger(uiState.value.payedCards.toString())
                    LastCards(
                        modifier = Modifier,
                        payedCards = filteredPayedCards,
                        onClickCard = { card ->
                            onEventDispatchers(TransferContract.Intent.ToP2PScreen(card.pan, card.ownerName))
                        }
                    )
                }
            } else {
                DefaultState(
                    onEventDispatcher = onEventDispatchers,
                    lastPayedCards = uiState.value.payedCards,
                    cardTemplates = uiState.value.templateCards,
                )
            }

            Spacer(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .height(Constants.BOTTOM_NAVIGATION_HEIGHT.dp)
            )
        }
    }
}


@Preview(showBackground = true, device = "id:pixel_7_pro")
@Composable
fun TransactionsScreenPreview() {
    MobileBankingTheme {
        TransactionsScreenContent(previewStateOf(value = TransferContract.UIState("Tohir Mirxomitov")), {})
    }
}