package uz.gita.mobilebanking.presentation.add_template

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.presentation.add_template.component.TemplateNameInput
import uz.gita.mobilebanking.presentation.transfers.components.LastCards
import uz.gita.mobilebanking.presentation.transfers.components.SearchBar
import uz.gita.mobilebanking.ui.components.TopBarWithBack
import uz.gita.mobilebanking.ui.components.buttons.NextButton
import uz.gita.mobilebanking.ui.components.cards.CardP2PWithCardNumber
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.theme.GrayIcon
import uz.gita.mobilebanking.utils.logger

class AddTemplateScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: AddTemplateContract.Model = getViewModel<AddTemplateModel>()
        CardInfoContent(viewModel.collectAsState(), viewModel::onEventDispatcher)
    }
}

@Composable
fun CardInfoContent(
    uiState: State<AddTemplateContract.UIState>,
    onEventDispatcher: (AddTemplateContract.Intent) -> Unit
) {
    Scaffold(topBar = {
        TopBarWithBack(
            modifier = Modifier.height(60.dp),
            title = "Shablon qo'shish",
            onClickIcon = {
                onEventDispatcher(AddTemplateContract.Intent.Back)
            }
        )
    }
    ) { paddingValues ->
        when (uiState.value) {
            is AddTemplateContract.UIState.ChooseCardTemplate ->
                ChooseCardState(uiState, onEventDispatcher, paddingValues)

            is AddTemplateContract.UIState.AddTemplateName ->
                NameCardState(uiState, onEventDispatcher, paddingValues)
        }
    }
}

@Composable
fun ChooseCardState(
    uiState: State<AddTemplateContract.UIState>,
    onEventDispatcher: (AddTemplateContract.Intent) -> Unit,
    paddingValues: PaddingValues
) {
    var searchText by remember { mutableStateOf("") }
    var isSearchingStateActive by remember { mutableStateOf(false) }
    var isSearchBarFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    var filteredPayedCards by remember { mutableStateOf((uiState.value as AddTemplateContract.UIState.ChooseCardTemplate).lastPayedCards) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        SearchBar(
            modifier = Modifier
                .padding(horizontal = 12.dp),
            onClickContacts = {},
            onClickScan = {},
            onValueChange = {
                if (it.length <= 16) {
                    searchText = it

                    filteredPayedCards = (uiState.value as AddTemplateContract.UIState.ChooseCardTemplate)
                        .lastPayedCards.filter { it.pan.startsWith(searchText) }
                }

                if (searchText.length == 16) {
                    // Card not found yoki karta
                } else {

                }
            },
            focusRequester = focusRequester,
            onFocusChanged = {
                isSearchBarFocused = it.isFocused
                if (isSearchBarFocused) isSearchingStateActive = true
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 12.dp, bottom = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextBoldBlack(
                text = "So'nggi",
                modifier = Modifier,
                fontSize = 18.sp,
                letterSpacing = 0.8.sp
            )
            Spacer(modifier = Modifier.weight(1f))

            TextBold(
                text = "Tozalash",
                color = GrayIcon,
                modifier = Modifier.padding(end = 6.dp),
                letterSpacing = 0.8.sp
            )
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.Delete),
                contentDescription = null,
                tint = GrayIcon
            )
        }

        LastCards(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(top = 12.dp),
            payedCards = filteredPayedCards,
            onClickCard = { card ->
                onEventDispatcher(AddTemplateContract.Intent.AddTemplate(card))
            }
        )
    }
}

@Composable
fun NameCardState(
    uiState: State<AddTemplateContract.UIState>,
    onEventDispatcher: (AddTemplateContract.Intent) -> Unit,
    paddingValues: PaddingValues
) {
    var templateName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        TemplateNameInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp)
                .height(52.dp),
            inputText = templateName,
            getInput = {
                logger(templateName)
                templateName = it
            }
        )

        CardP2PWithCardNumber(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            onClickItem = {
                onEventDispatcher(AddTemplateContract.Intent.ChangeUIToCardsState)
            },
            cardNumber = (uiState.value as AddTemplateContract.UIState.AddTemplateName).card.pan,
            ownerName = (uiState.value as AddTemplateContract.UIState.AddTemplateName).card.ownerName,
        )

        Spacer(modifier = Modifier.weight(1f))

        NextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            isEnabled = true,
            onClick = {
                onEventDispatcher(AddTemplateContract.Intent.AddTemplate(card = (uiState.value as AddTemplateContract.UIState.AddTemplateName).card))
            }
        )
    }
}

