package uz.gita.mobilebanking.presentation.verify

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
import androidx.compose.runtime.getValue
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
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.CodeTextField
import uz.gita.mobilebanking.ui.components.buttons.ButtonWithTimer
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormalBlack
import uz.gita.mobilebanking.ui.theme.errorColor
import uz.gita.mobilebanking.ui.theme.pinScreenBgLight
import uz.gita.mobilebanking.ui.theme.textColor

data class VerifyScreen(val token: String, val isSignIn: Boolean) : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<VerifyModel>()

        VerifyContent(
            viewModel::onEventDispatcher,
            token,
            isSignIn
        )
    }
}

@Composable
fun VerifyContent(
    onEventDispatcher: (VerifyContract.Intent) -> Unit,
    token: String,
    isSignIn: Boolean
) {

    var verificationCode by remember { mutableStateOf("") }
    var buttonSt by remember { mutableStateOf(false) }
    var isIncorrect by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(color = pinScreenBgLight),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = {},
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
            contentDescription = "lock icon",
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
                text = "+998 90 --- -- 20", fontSize = 12.sp, color = textColor, letterSpacing = 0.8.sp
            )

            TextNormalBlack(
                text = stringResource(R.string.sent_to_number),
                fontSize = 12.sp,
                letterSpacing = 0.8.sp,
                color = textColor
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
                    //isIncorrect = true

                    onEventDispatcher(VerifyContract.Intent.CheckUserCode(token, verificationCode, isSignIn))
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

        ButtonWithTimer(
            Modifier
                .padding(12.dp)
                .height(60.dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun VerifyPreview() {
    VerifyContent({}, "", false)
}