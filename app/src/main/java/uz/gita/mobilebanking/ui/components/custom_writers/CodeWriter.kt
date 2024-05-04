package uz.gita.mobilebanking.ui.components.custom_writers

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun CodeWriter(
    modifier: Modifier,
    correctUserPin: String,
    @StringRes forgetPinCodeText: Int,
    @DrawableRes clearIcon: Int,
    @DrawableRes fingerIcon: Int,
    onPinCodeForgetClick: () -> Unit,
    correctPinCodeListener: () -> Unit,
    incorrectPinCodeListener: () -> Unit,
    onFingerButtonClick: () -> Unit,
    circleCheckedColor: Color = uz.gita.mobilebanking.ui.theme.primaryColor,
    circleDefaultColor: Color = uz.gita.mobilebanking.ui.theme.circleDefaultColor,
    circleErrorColor: Color = uz.gita.mobilebanking.ui.theme.errorColor,
    listOfCircleColors: MutableList<Color>,
    onChangeCircleColor: (MutableList<Color>) -> Unit
) {
    var currentPos by remember { mutableIntStateOf(-1) }
    val currentPassword by remember { mutableStateOf(StringBuilder()) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        for (i in 0..2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (j in 1..3) {
                    PinCodeNumberItem(
                        modifier = Modifier,
                        number = "${i * 3 + j}",
                        onClick = { number ->
                            if (currentPos < correctUserPin.length - 1) {
                                currentPassword.append(number)
                                listOfCircleColors[++currentPos] = circleCheckedColor
                                onChangeCircleColor(listOfCircleColors)

                                if (currentPos == correctUserPin.length - 1) {
                                    if (correctUserPin == currentPassword.toString()) {
                                        correctPinCodeListener()
                                    } else {
                                        incorrectPinCodeListener()
                                        currentPassword.clear()
                                        currentPos = -1
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ForgetPinButton(
                modifier = Modifier, onClick = {
                    onPinCodeForgetClick()
                },
                text = stringResource(id = forgetPinCodeText),
                fontSize = 8
            )
            PinCodeNumberItem(
                modifier = Modifier,
                number = "0",
                onClick = { number ->
                    if (currentPos < correctUserPin.length - 1) {
                        currentPassword.append(number)
                        listOfCircleColors[++currentPos] = circleCheckedColor
                        onChangeCircleColor(listOfCircleColors)

                        if (currentPos == correctUserPin.length - 1) {
                            if (correctUserPin == currentPassword.toString()) {
                                correctPinCodeListener()
                            } else {
                                incorrectPinCodeListener()
                            }
                        }
                    }
                }
            )

            if (currentPos == -1) {
                RightBottomItem(
                    modifier = Modifier,
                    onClick = {
                        onFingerButtonClick()
                    },
                    iconID = fingerIcon
                )
            } else {
                RightBottomItem(
                    modifier = Modifier,
                    onClick = {
                        currentPassword.setLength(currentPassword.length - 1)
                        listOfCircleColors[currentPos--] = circleDefaultColor
                        onChangeCircleColor(listOfCircleColors)
                    },
                    iconID = clearIcon
                )
            }
        }
    }
}
