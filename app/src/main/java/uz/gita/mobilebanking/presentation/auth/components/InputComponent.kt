package uz.gita.mobilebanking.presentation.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.ui.theme.AuthComponentBg
import uz.gita.mobilebanking.ui.theme.errorColor
import uz.gita.mobilebanking.ui.theme.textColor


@Composable
fun PhoneInputComponent(
    text: String = "",
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String = "Title",
    leadingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    error: String? = null,
    onTextChanged: (value: String) -> Unit = {}
) {
    Column(modifier = modifier) {

        Text(
            label,
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

//        Spacer(modifier = Modifier.padding(top = 4.dp))

        BasicTextField(
            value = text,
            onValueChange = {
                if (it.length <= 9) {
                    onTextChanged(it)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            enabled = enabled,
            readOnly = readOnly,
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold))
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = PhoneMaskTransformation,
            cursorBrush = SolidColor(getColorX(colorName = textColor)),
            decorationBox = { innerTextField ->

                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(getColorX(colorName = AuthComponentBg))
                        .border(
                            width = 1.dp,
                            color = if (isError) getColorX(colorName = errorColor) else getColorX(
                                colorName = AuthComponentBg
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Row(modifier = Modifier.padding()) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_flag_uz),
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .height(24.dp)
                                .align(Alignment.CenterVertically),
                            contentDescription = null
                        )
                        Icon(
                            painter = painterResource(id = com.chuckerteam.chucker.R.drawable.chucker_ic_arrow_down),
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterVertically),
                            contentDescription = null,
                            tint = textColor
                        )
                        leadingIcon?.invoke()
                        Box(
                            modifier = Modifier
                                .padding(
                                    start = 8.dp,
                                    end = 8.dp

                                )
                                .align(Alignment.CenterVertically)
                        ) {
                            innerTextField()
                        }
                    }
                }

            }
        )
        if (isError) {
            Text(
                error ?: stringResource(id = R.string.connection_error),
//                style = Typography.headlineLarge
            )
        }
    }
}

fun getColorX(colorName: Color): Color {
    return colorName
}

@Preview(showBackground = true)
@Composable
private fun PhoneInputComponentPreview() {
    MobileBankingTheme {
        var phoneSt by remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current

        PhoneInputComponent(
            label = stringResource(id = R.string.your_phone_number),
            text = phoneSt,
            leadingIcon = {
                Text(
                    stringResource(id = R.string.phone_prefix),
                    modifier = Modifier
                        .padding(start = 16.dp),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                )
            },
            modifier = Modifier.padding(top = 48.dp),
            onTextChanged = {
                phoneSt = it
                if (it.length < 9) {
                    //
                } else focusManager.clearFocus()
            },
            enabled = true,
            isError = false,
            error = ""
        )
    }
}


