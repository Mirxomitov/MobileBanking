package uz.gita.mobilebanking.presentation.addcard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.ui.theme.errorColor2
import uz.gita.mobilebanking.ui.theme.grayColor
import uz.gita.mobilebanking.ui.theme.mainBgLight
import uz.gita.mobilebanking.utils.angledGradientBackground
import uz.gita.mobilebanking.utils.checkExpirationDateValidation
import uz.gita.mobilebanking.utils.logger
import uz.gita.mobilebanking.utils.visual_transformations.CardNumberTransformation

class AddCardScreen : Screen {
    @Composable
    override fun Content() {
        MobileBankingTheme {
            val viewModel: AddCardContract.Model = getViewModel<AddCardModel>()
            AddCardContent(
                viewModel.collectAsState().value, viewModel::onEventDispatchers
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddCardContent(
    uiState: AddCardContract.UIState, onEventDispatcher: (AddCardContract.Intent) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var cardNumber by remember { mutableStateOf("") }
    var expirationDate by remember { mutableStateOf("") }

    var isExpirationDateValid by remember { mutableStateOf(true) }
    var isCardNumberFocused by remember { mutableStateOf(false) }
    var isExpirationDateFocused by remember { mutableStateOf(false) }

    Scaffold(containerColor = mainBgLight, topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = mainBgLight,
                titleContentColor = Color.Black,
            ),
            navigationIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    Modifier
                        .padding(12.dp)
                        .size(24.dp)
                        .clickable(interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                onEventDispatcher(AddCardContract.Intent.Back)
                            })
                )
            },
            title = {
                TextBoldBlack(text = stringResource(id = R.string.add_card_title), letterSpacing = 0.8.sp)
            },
        )
    }) {
        Column(Modifier.padding(it)) {
            // card
            Column(
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(ratio = 1.5857725f)
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .angledGradientBackground(
                        colors = listOf(
                            Color(0xFF0A5EAA), Color(0xFF42E4C7)
                        ), 75f
                    )
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(mainBgLight)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(modifier = Modifier) {
                        if (cardNumber == "")
                            TextNormal(
                                fontSize = 18.sp,
                                color = if (isCardNumberFocused) Color.Black else grayColor,
                                text = "Karta raqami",
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                            )

                        BasicTextField(
                            singleLine = true,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .focusRequester(focusRequester)
                                .onFocusChanged {
                                    isCardNumberFocused = it.isFocused
                                },
                            value = cardNumber,
                            onValueChange = {
                                if (it.length <= 16) {
                                    cardNumber = it
                                    if (it.length == 16) focusManager.moveFocus(FocusDirection.Down)
                                }
                            },
                            visualTransformation = CardNumberTransformation,
                            textStyle = TextStyle(fontSize = 18.sp),
                            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    if (cardNumber == "") Icon(
                        painter = painterResource(id = R.drawable.ic_action_scan_card),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { onEventDispatcher(AddCardContract.Intent.ToScanCardScreen) }
                            )
                    )
                    else if (isCardNumberFocused) // cardNumber != ""
                        Icon(
                            painter = painterResource(id = R.drawable.search_clear),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = { cardNumber = "" }
                                )
                        )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .height(60.dp)
                        .background(mainBgLight)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(modifier = Modifier) {
                        if (expirationDate == "")
                            TextNormal(
                                fontSize = 18.sp,
                                color = if (isExpirationDateFocused) Color.Black else grayColor,
                                text = "oo/yy",
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                        BasicTextField(
                            singleLine = true,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .focusRequester(focusRequester)
                                .onFocusChanged { isExpirationDateFocused = it.isFocused },
                            value = expirationDate,
                            onValueChange = {
                                logger("card expiration number : $it")
                                if (it.length < 2) {
                                    expirationDate = it
                                } else if (it.length == 2) {
                                    expirationDate = it.substring(0, 2) + "/"
                                } else {
                                    expirationDate = it.substring(0, 2) + "/" + it.substring(2, it.length)
                                    isExpirationDateValid = true

                                    if (it.length == 5) isExpirationDateValid =
                                        expirationDate.checkExpirationDateValidation()
                                }
                            },
                            visualTransformation = VisualTransformation.None,
                            textStyle = TextStyle(
                                fontSize = 18.sp,
                                color = if (isExpirationDateValid) Color.Black else errorColor2
                            ),
                            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }
            }

            if (!isExpirationDateValid) {
                TextNormal(
                    modifier = Modifier.padding(start = 20.dp, top = 8.dp),
                    text = stringResource(R.string.invalid_card_expiration_date),
                    color = errorColor2
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Button
        }
    }
}

@[Preview Composable]
fun AddCardPreview() {
    AddCardContent(AddCardContract.UIState, {})
}