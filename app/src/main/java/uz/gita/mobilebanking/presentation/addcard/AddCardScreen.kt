@file:OptIn(ExperimentalPermissionsApi::class)

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
import androidx.compose.runtime.State
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.presentation.addcard.components.AddCardButton
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.dialogs.CameraPermissionRationaleDialog
import uz.gita.mobilebanking.ui.theme.MainBgLight
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.ui.theme.errorColor2
import uz.gita.mobilebanking.ui.theme.grayColor
import uz.gita.mobilebanking.utils.angledGradientBackground
import uz.gita.mobilebanking.utils.checkExpirationDateValidation
import uz.gita.mobilebanking.utils.containsOnlyNumbers
import uz.gita.mobilebanking.utils.logger
import uz.gita.mobilebanking.utils.previewStateOf
import uz.gita.mobilebanking.utils.visual_transformations.CardNumberTransformation
import uz.gita.mobilebanking.utils.visual_transformations.ExpirationDateTransformation

data class AddCardScreen(
    val cardNumber: String = "",
    val expirationDate: String = ""
) : Screen {
    @Composable
    override fun Content() {
        MobileBankingTheme {
            val viewModel: AddCardContract.Model = getViewModel<AddCardModel>()
            viewModel.onEventDispatchers(AddCardContract.Intent.SaveCard(cardNumber, expirationDate))

            AddCardContent(
                viewModel.collectAsState(),
                viewModel::onEventDispatchers
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddCardContent(
    uiState: State<AddCardContract.UIState>,
    onEventDispatcher: (AddCardContract.Intent) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var cardNumber by remember { mutableStateOf(uiState.value.cardNumber) }
    var expirationDate by remember { mutableStateOf(uiState.value.expirationDate) }

    var isExpirationErrorVisible by remember { mutableStateOf(false) }
    var isCardNumberFocused by remember { mutableStateOf(false) }
    var isExpirationDateFocused by remember { mutableStateOf(false) }

    val bottomSheetNavigator = LocalBottomSheetNavigator.current
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    Scaffold(containerColor = MainBgLight, topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MainBgLight,
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
                            }
                        )
                )
            },
            title = {
                TextBoldBlack(text = stringResource(id = R.string.add_card_title), letterSpacing = 0.8.sp)
            },
        )
    }
    ) {
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
                        .background(MainBgLight)
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
                                if (it.length <= 16 && it.containsOnlyNumbers()) {
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
                                onClick = {
                                    if (!cameraPermissionState.status.shouldShowRationale && !cameraPermissionState.status.isGranted) {
                                        cameraPermissionState.launchPermissionRequest()
                                    } else if (cameraPermissionState.status.isGranted) {
                                        onEventDispatcher(AddCardContract.Intent.ToScanCardScreen)
                                    } else if (cameraPermissionState.status.shouldShowRationale) {
                                        bottomSheetNavigator.show(CameraPermissionRationaleDialog(onHide = { bottomSheetNavigator.hide() }))
                                    }
                                }
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
                        .background(MainBgLight)
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
                                if (it.length < 5) {
                                    expirationDate = it
                                    isExpirationErrorVisible = false
                                }
                                if (it.length == 4) {
                                    isExpirationErrorVisible = !expirationDate.checkExpirationDateValidation()
                                }
                            },
                            visualTransformation = ExpirationDateTransformation,
                            textStyle = TextStyle(
                                fontSize = 18.sp,
                                color = if (!isExpirationErrorVisible) Color.Black else errorColor2
                            ),
                            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }
            }

            if (isExpirationErrorVisible) {
                TextNormal(
                    modifier = Modifier.padding(start = 20.dp, top = 8.dp),
                    text = stringResource(R.string.invalid_card_expiration_date),
                    color = errorColor2
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            logger("${cardNumber.length == 16} && ${expirationDate.length == 4} && ${!isExpirationErrorVisible}")

            AddCardButton(
                modifier = Modifier,
                isEnabled = cardNumber.length == 16 && expirationDate.length == 4 && !isExpirationErrorVisible,
                onClick = {
                    onEventDispatcher(
                        AddCardContract.Intent.AddCard(
                            cardNumber.trim(),
                            expirationDate.trim()
                        )
                    )
                }
            )
        }
    }
}

@[Preview Composable]
fun AddCardPreview() {
    AddCardContent(previewStateOf(value = AddCardContract.UIState()), {})
}