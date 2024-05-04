package uz.gita.mobilebanking.presentation.pin_check

import android.widget.Toast
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormalBlack
import uz.gita.mobilebanking.ui.components.custom_writers.CodeChecker
import uz.gita.mobilebanking.ui.components.custom_writers.PinCodeCircle
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.ui.theme.circleDefaultColor
import uz.gita.mobilebanking.ui.theme.pinScreenBgLight
import uz.gita.mobilebanking.ui.theme.textColor
import uz.gita.mobilebanking.utils.logger

class PinCheckScreen : Screen {
    @Composable
    override fun Content() {
        MobileBankingTheme {
            val viewModel: PinCheckContract.Model = getViewModel<PinCheckModel>()

            PinCheckContent(viewModel::onEventDispatcher)
        }
    }
}

@Composable
private fun PinCheckContent(
    onEventDispatcher: (PinCheckContract.Intent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(pinScreenBgLight)
    ) {
        val context = LocalContext.current
        val PASSWORD_LENGTH = 4
        val listOfCircleColors =
            remember { mutableStateListOf<Color>().apply { repeat(PASSWORD_LENGTH) { this.add(circleDefaultColor) } } }

        Column(
            modifier = Modifier
                .fillMaxSize(),
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
                text = stringResource(id = R.string.repeat_pin_code), fontSize = 22.sp, letterSpacing = 0.8.sp
            )

            TextNormalBlack(
                text = stringResource(id = R.string.your_phone_number),
                fontSize = 12.sp,
                letterSpacing = 0.8.sp,
                color = textColor
            )

            TextNormalBlack(
                text = "+998 90 --- -- 20", fontSize = 12.sp, color = textColor, letterSpacing = 0.8.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            CodeChecker(modifier = Modifier,
                onCorrectPinCodeListener = {
                    Toast.makeText(context, "CORRECT", Toast.LENGTH_SHORT).show()
                    onEventDispatcher(PinCheckContract.Intent.ToMainScreen)
                },
                onIncorrectPinCodeListener = {
                    Toast.makeText(context, "INCORRECT", Toast.LENGTH_SHORT).show()
                },
                correctUserPin = "1234",
                listOfCircleColors = listOfCircleColors,
                onChangeCircleColor = {
                    it.forEachIndexed { index, color ->
                        listOfCircleColors[index] = listOfCircleColors[index].copy(
                            alpha = color.alpha, blue = color.blue, green = color.green, red = color.red
                        )
                    }
                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(PASSWORD_LENGTH) {
                logger("draw circles")
                PinCodeCircle(color = listOfCircleColors[it], radius = 10, 12)
            }
        }
    }
}

@Preview
@Composable
fun PinCheckPreview() {
    PinCheckContent({})
}