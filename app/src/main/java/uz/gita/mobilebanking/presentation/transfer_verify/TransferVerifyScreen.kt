package uz.gita.mobilebanking.presentation.transfer_verify

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.CodeTextField
import uz.gita.mobilebanking.ui.components.buttons.ButtonWithTimer
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormalBlack
import uz.gita.mobilebanking.ui.theme.errorColor
import uz.gita.mobilebanking.ui.theme.pinScreenBgLight
import uz.gita.mobilebanking.ui.theme.TextColor
import uz.gita.mobilebanking.utils.hidePartOfNumber
import uz.gita.mobilebanking.utils.previewStateOf


data class TransferVerifyScreen(
    val token: String,
    val amount : String,
    val receiverName : String,
    val receiverPan : String,
) : Screen {
    @Composable
    override fun Content() {
        val viewModel: TransferVerifyContract.Model = getViewModel<TransferVerifyModel>()
        viewModel.onEventDispatcher(TransferVerifyContract.Intent.SaveToken(token))

        viewModel.saveData(token, amount, receiverName, receiverPan)

        VerifyContent(
            viewModel.collectAsState(),
            viewModel::onEventDispatcher,
        )
    }
}

@Composable
fun VerifyContent(
    uiState: State<TransferVerifyContract.UIState>,
    onEventDispatcher: (TransferVerifyContract.Intent) -> Unit,
) {
    var verificationCode by remember { mutableStateOf("") }
    var buttonSt by remember { mutableStateOf(false) }
    val isIncorrect by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .fillMaxSize()
            .background(color = pinScreenBgLight)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = { onEventDispatcher(TransferVerifyContract.Intent.Back) },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
            )
        }

        Image(
            painter = painterResource(id = R.drawable.paynet),
            contentDescription = null,
            Modifier
                .padding(bottom = 24.dp)
                .size(60.dp)
        )

        TextBoldBlack(
            text = stringResource(id = R.string.add_code_in_sms),
            fontSize = 22.sp,
            letterSpacing = 0.8.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Row {
            TextNormalBlack(
                text = uiState.value.phoneNumber.hidePartOfNumber(),
                fontSize = 12.sp,
                color = TextColor,
                letterSpacing = 0.8.sp
            )

            TextNormalBlack(
                text = stringResource(R.string.sent_to_number),
                fontSize = 12.sp,
                letterSpacing = 0.8.sp,
                color = TextColor
            )
        }

        CodeTextField(
            value = verificationCode,
            length = 6,
            onValueChange = {
                verificationCode = it
                if (it.length < 6) {
                    buttonSt = false
                } else {
                    buttonSt = true
                    focusManager.clearFocus()

                    onEventDispatcher(
                        TransferVerifyContract.Intent.CheckUserCode(
                            uiState.value.token,
                            verificationCode
                        )
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.padding(top = 12.dp),
        )

        if (isIncorrect)
            TextBold(
                text = stringResource(R.string.incorrect_code_error_text),
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                color = errorColor,
                letterSpacing = 0.8.sp,
                textAlign = TextAlign.Center
            )

        Spacer(modifier = Modifier.weight(1f))

        var resendTime by remember { mutableIntStateOf(60) }

        ButtonWithTimer(
            Modifier
                .padding(12.dp)
                .height(60.dp)
                .fillMaxWidth(),
            leftIcon = R.drawable.ic_reload,
            time = resendTime,
            onClick = {
                if (resendTime == 60) {
                    resendTime--
                    onEventDispatcher(TransferVerifyContract.Intent.ResendSms(uiState.value.token))
                }
            },
            isSecondsVisible = resendTime != 60
        )

        LaunchedEffect(resendTime) {
            while (true) {
                delay(1000)
                if (resendTime == 1) {
                    resendTime = 60
                    break
                } else if (resendTime in 1..59) {
                    resendTime--
                }
            }
        }
    }
}

@Preview
@Composable
private fun VerifyPreview() {
    VerifyContent(
        uiState = previewStateOf(value = TransferVerifyContract.UIState(phoneNumber = "903553620", token = "TOKEN")),
        onEventDispatcher = {}
    )
}
