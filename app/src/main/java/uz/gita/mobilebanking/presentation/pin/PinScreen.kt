package uz.gita.mobilebanking.presentation.pin

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.components.custom_text.TextNormalBlack
import uz.gita.mobilebanking.ui.components.custom_writers.CodeWriter
import uz.gita.mobilebanking.ui.components.custom_writers.PinCodeCircle
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.ui.theme.circleDefaultColor
import uz.gita.mobilebanking.ui.theme.errorColor
import uz.gita.mobilebanking.ui.theme.pinScreenBgLight
import uz.gita.mobilebanking.ui.theme.textColor
import uz.gita.mobilebanking.utils.hidePartOfNumber

class PinScreen : Screen {
    @Composable
    override fun Content() {
        MobileBankingTheme {
            val viewModel: PinContract.Model = getViewModel<PinModel>()
            PinContent(
                viewModel.collectAsState().value,
                viewModel::onEventDispatcher
            )
        }
    }
}

@Composable
fun PinContent(
    uiState: PinContract.UIState,
    onEventDispatcher: (PinContract.Intent) -> Unit
) {

    val ATTEMPTS_COUNT = 3
    val PASSWORD_LENGTH = 4
    var attemptsCount by remember { mutableIntStateOf(ATTEMPTS_COUNT) }
    var isErrorTextVisible by remember { mutableStateOf(false) }
    val listOfCircleColors =
        remember {
            mutableStateListOf<Color>().apply { repeat(PASSWORD_LENGTH) { this.add(circleDefaultColor) } }
        }

    val shakeOffset = remember { Animatable(0f) }

    LaunchedEffect(attemptsCount) {
        if (attemptsCount != ATTEMPTS_COUNT) {
            repeat(4) {
                shakeOffset.animateTo(
                    targetValue = 20f,
                    animationSpec = tween(durationMillis = 20, easing = LinearEasing)
                )
                shakeOffset.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 20, easing = LinearEasing)
                )
            }

            changeColor(circleDefaultColor, listOfCircleColors)
        }
    }


    // ui started here
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(pinScreenBgLight),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(pinScreenBgLight),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

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
                text = uiState.phoneNumber.hidePartOfNumber(),
                fontSize = 12.sp,
                color = textColor,
                letterSpacing = 0.8.sp
            )


            if (isErrorTextVisible) {
                TextNormal(
                    modifier = Modifier.padding(top = 24.dp),
                    text = stringResource(id = R.string.incorrect_pin_code),
                    color = errorColor,
                )
                TextNormal(
                    text = "$attemptsCount " + stringResource(id = R.string.count_attempt_left),
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            CodeWriter(
                modifier = Modifier,
                correctUserPin = uiState.pinCode,
                forgetPinCodeText = R.string.forget_pin_code,
                clearIcon = R.drawable.ic_keyboard_remove,
                fingerIcon = R.drawable.ic_fingerprint,
                onPinCodeForgetClick = {

                },
                correctPinCodeListener = {
                    onEventDispatcher(PinContract.Intent.ToMainScreen)
                },
                incorrectPinCodeListener = {
                    changeColor(errorColor, listOfCircleColors)
                    attemptsCount--
                    isErrorTextVisible = true
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
                }
            )
        }

        Row(
            modifier = Modifier
                .padding(horizontal = shakeOffset.value.dp)
               ,
        ) {
            repeat(PASSWORD_LENGTH) {
                PinCodeCircle(
                    color = listOfCircleColors[it], radius = 15,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PinPreview() {
    PinContent(
        uiState = PinContract.UIState("1234", "903553620"),
        onEventDispatcher = {}
    )
}

private fun changeColor(color: Color, listOfCircleColors: MutableList<Color>) {
    listOfCircleColors.forEachIndexed { index, _ ->
        listOfCircleColors[index] = listOfCircleColors[index].copy(
            alpha = color.alpha,
            blue = color.blue,
            green = color.green,
            red = color.red
        )
    }
}