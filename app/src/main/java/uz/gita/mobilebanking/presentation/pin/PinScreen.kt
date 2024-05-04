package uz.gita.mobilebanking.presentation.pin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.components.custom_text.TextNormalBlack
import uz.gita.mobilebanking.ui.components.custom_writers.CodeWriter
import uz.gita.mobilebanking.ui.components.custom_writers.PinCodeCircle
import uz.gita.mobilebanking.ui.theme.circleDefaultColor
import uz.gita.mobilebanking.ui.theme.errorColor
import uz.gita.mobilebanking.ui.theme.pinScreenBgLight
import uz.gita.mobilebanking.ui.theme.textColor
import uz.gita.mobilebanking.utils.logger

class PinScreen : Screen {
    @Composable
    override fun Content() {
        PinContent()
    }
}

@Composable
fun PinContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(pinScreenBgLight),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val ATTEMPTS_COUNT = 3
        val PASSWORD_LENGTH = 4
        var attemptsCount by remember { mutableIntStateOf(ATTEMPTS_COUNT) }
        var isErrorTextVisible by remember { mutableStateOf(false) }
        val listOfCircleColors =
            remember {
                mutableStateListOf<Color>().apply { repeat(PASSWORD_LENGTH) { this.add(circleDefaultColor) } }
            }

        Spacer(modifier = Modifier.height(64.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_lock),
            contentDescription = "lock icon",
            Modifier
                .padding(bottom = 16.dp)
                .size(60.dp)
        )

        TextBoldBlack(
            text = stringResource(id = R.string.enter_the_pin_code),
            fontSize = 22.sp,
            letterSpacing = 0.8.sp
        )

        TextNormalBlack(
            text = stringResource(id = R.string.your_phone_number),
            fontSize = 12.sp,
            letterSpacing = 0.8.sp,
            color = textColor
        )

        TextNormalBlack(
            text = "+998 90 --- -- 20",
            fontSize = 12.sp,
            color = textColor,
            letterSpacing = 0.8.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        if (isErrorTextVisible) {
            TextNormal(
                text = stringResource(id = R.string.incorrect_pin_code),
                color = errorColor,
            )
            TextNormal(
                text = "$attemptsCount " + stringResource(id = R.string.count_attempt_left),
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,

        ) {
            repeat(PASSWORD_LENGTH) {
                logger("draw circles")
                PinCodeCircle(color = listOfCircleColors[it], radius = 10, 12)
            }
        }

        Spacer(modifier = Modifier.weight(if (isErrorTextVisible) 2f else 1f))

        CodeWriter(
            modifier = Modifier,
            correctUserPin = "1234",
            forgetPinCodeText = R.string.forget_pin_code,
            clearIcon = R.drawable.ic_keyboard_remove,
            fingerIcon = R.drawable.ic_fingerprint,
            onPinCodeForgetClick = {

            },
            correctPinCodeListener = {
                logger("correct pin code")
            },
            incorrectPinCodeListener = {
                attemptsCount--
                isErrorTextVisible = true

                logger(listOfCircleColors.joinToString { it.green.toString() })
            },
            onFingerButtonClick = {

            },
            listOfCircleColors = listOfCircleColors,
            onChangeCircleColor = {
                it.forEachIndexed { index, color ->
                    listOfCircleColors[index] = listOfCircleColors[index].copy(
                        alpha = color.alpha,
                        blue = color.blue,
                        green = color.green,
                        red = color.red
                    )
                }

                logger("changing -> " + listOfCircleColors.joinToString { it.green.toString() })
            }
        )
    }
}

@Preview
@Composable
fun PinPreview() {
    PinContent()
}