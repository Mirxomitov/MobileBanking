package uz.gita.mobilebanking.presentation.p2p

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.model.ui.CardData
import uz.gita.mobilebanking.presentation.p2p.components.P2PMoneyInputWithTitle
import uz.gita.mobilebanking.ui.components.TopBarWithBack
import uz.gita.mobilebanking.ui.components.buttons.NextButton
import uz.gita.mobilebanking.ui.components.cards.CardP2PSendItem
import uz.gita.mobilebanking.ui.components.cards.CardP2PWithCardNumber
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.dialogs.ChangeCard
import uz.gita.mobilebanking.ui.theme.p2pScreenBg
import uz.gita.mobilebanking.utils.logger
import uz.gita.mobilebanking.utils.previewStateOf

data class P2PScreen(val receiverPan: String, val ownerName: String) : Screen {
    @Composable
    override fun Content() {
        val viewModel: P2PContract.Model = getViewModel<P2PModel>()
        logger("$receiverPan, $ownerName")
        viewModel.onEventDispatcher(P2PContract.Intent.SaveReceiverData(receiverPan, ownerName))

        P2PContent(
            viewModel.collectAsState(),
            viewModel::onEventDispatcher
        )
    }
}

@Composable
private fun P2PContent(
    uiState: State<P2PContract.UIState>,
    onEventDispatcher: (P2PContract.Intent) -> Unit,
) {
    val isInputIncorrect by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    var isTransferButtonEnabled by remember { mutableStateOf(true) }
    var transferAmount by remember { mutableStateOf(0) }
    val bottomSheetNavigator = LocalBottomSheetNavigator.current

    Column(
        Modifier
            .fillMaxSize()
            .background(color = p2pScreenBg)
            .padding(12.dp)
    ) {
        TopBarWithBack(
            modifier = Modifier,
            title = stringResource(R.string.transfer_to_card),
            onClickIcon = { onEventDispatcher(P2PContract.Intent.Back) }
        )

        TextBoldBlack(
            text = stringResource(R.string.from),
            modifier = Modifier.padding(top = 18.dp),
            fontSize = 18.sp,
            letterSpacing = 0.8.sp
        )

        CardP2PSendItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            onClickItem = {
                bottomSheetNavigator.show(
                    ChangeCard(
                        cards = uiState.value.cards,
                        onClickAddCard = {
                            bottomSheetNavigator.hide()
                            onEventDispatcher(P2PContract.Intent.AddCard)
                        },
                        onClickCard = {},
                    )
                )
            },
            cardData = CardData("", "0004", "", "", "Personal", "1000000")
        )

        TextBoldBlack(
            text = stringResource(R.string.to),
            modifier = Modifier.padding(top = 24.dp),
            fontSize = 18.sp,
            letterSpacing = 0.8.sp
        )

        CardP2PWithCardNumber(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            onClickItem = {},
            cardNumber = uiState.value.receiverPan,
            ownerName = uiState.value.ownerName,
        )

        P2PMoneyInputWithTitle(
            modifier = Modifier
                .padding(top = 24.dp),
            onError = {},
            focusRequester = focusRequester,
            onValueChange = {
                try {
                    // TODO make input logic
                    isTransferButtonEnabled = (it.toInt() in 1_000..34_000_000)
                    transferAmount = it.toInt()
                } catch (e: Exception) {
                }
            }
        )
        TextNormal(
            modifier = Modifier.padding(8.dp),
            text = stringResource(if (isInputIncorrect) R.string.commission_0 else R.string.insufficient_funds)
        )


        Spacer(modifier = Modifier.weight(1f))


        NextButton(
            modifier = Modifier.fillMaxWidth(),
            isEnabled = isTransferButtonEnabled,
            onClick = {
                onEventDispatcher(
                    P2PContract.Intent.Pay(
                        senderId = "32",
                        receiverPan = uiState.value.receiverPan,
                        amount = transferAmount
                    )
                )
            }
        )
    }

    logger("UIState: receiverPan = ${uiState.value.receiverPan}, ownerName = ${uiState.value.ownerName}),\n cards = ${uiState.value.cards}")
}

@Preview
@Composable
fun P2PPreview() {
    P2PContent(
        uiState = previewStateOf(value = P2PContract.UIState(receiverPan = "0002", ownerName = "Tohir Mirxomitov")),
        onEventDispatcher = {}
    )
}