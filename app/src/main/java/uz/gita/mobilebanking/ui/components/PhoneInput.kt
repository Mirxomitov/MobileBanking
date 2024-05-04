package uz.gita.mobilebanking.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.presentation.auth.components.PhoneMaskTransformation
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.theme.authComponentBg
import uz.gita.mobilebanking.ui.theme.grayColor
import uz.gita.mobilebanking.utils.logger

@Composable
fun PhoneInput(
    modifier: Modifier,
    inputText: String = "",
    getInput: (String) -> Unit,
    clear: (String) -> Unit
) {
    var phone: String by remember { mutableStateOf(inputText) }
    val focusRequester = FocusRequester()

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .background(authComponentBg)
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier, verticalAlignment = Alignment.CenterVertically
        ) {
            Flag(
                modifier = Modifier
            )

            TextBoldBlack(
                text = "+ 998 ", fontSize = 20.sp, modifier = Modifier.padding(start = 4.dp), letterSpacing = 0.8.sp
            )

            BasicTextField(
                value = phone,
                onValueChange = {
                    logger("phone.length=${phone.length}")
                    if (it.length <= 9) {
                        phone = it.trim()
                        getInput(phone)
                    }
                },
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Transparent)
                    .focusRequester(focusRequester)
                    .focusable(),
                textStyle = TextStyle.Default.copy(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = PhoneMaskTransformation
            )


            Icon(
                painter = painterResource(id = R.drawable.ic_remove),
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(24.dp)
                    .clickable(
                        onClick = {
                            logger("onClick.Clear")
                            phone = ""
                            clear(phone)
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ),
                tint = grayColor,
            )
        }
    }
}


@Composable
private fun Flag(
    modifier: Modifier,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_flag_uz),
            contentDescription = null,
            modifier = Modifier
                .height(32.dp)
                .width(56.dp)
                .clip(CircleShape)
        )

        Icon(
            painter = rememberVectorPainter(image = Icons.Default.KeyboardArrowDown),
            contentDescription = "down",
            modifier = Modifier.size(24.dp),
            tint = grayColor
        )
    }
}

@Preview
@Composable
fun PhoneInputPreview() {
//    PhoneInput(
//        Modifier, ""
//    ) {
//
//    }
}