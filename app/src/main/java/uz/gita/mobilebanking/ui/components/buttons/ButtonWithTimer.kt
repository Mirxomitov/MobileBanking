package uz.gita.mobilebanking.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.theme.authComponentBg
import uz.gita.mobilebanking.ui.theme.grayIcon
import uz.gita.mobilebanking.ui.theme.textColor

@Composable
fun ButtonWithTimer(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.send_new_code),
    @DrawableRes leftIcon: Int? = null
) {
    Button(
        onClick = { /*TODO*/ },
        modifier = modifier
            ,
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            disabledContentColor = authComponentBg,
            containerColor = authComponentBg,
            disabledContainerColor = authComponentBg,
        )
    ) {
        if (leftIcon != null) {
            Icon(
                painter = painterResource(id = leftIcon),
                contentDescription = null,
                Modifier.size(24.dp)
            )
        }

        TextBold(text = text, fontSize = 18.sp, color = grayIcon)

        Spacer(modifier = Modifier.width(8.dp))

        TextBold(text = "55", fontSize = 18.sp, color = textColor)
        TextBold(text = stringResource(R.string.sec), fontSize = 18.sp, color = textColor)
    }
}