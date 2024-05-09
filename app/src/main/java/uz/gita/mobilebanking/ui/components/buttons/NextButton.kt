package uz.gita.mobilebanking.ui.components.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.theme.PrimaryColor
import uz.gita.mobilebanking.ui.theme.PrimaryContainer

@Composable
fun NextButton(
    modifier: Modifier,
    isEnabled: Boolean,
    containerColor: Color,
    onClick: () -> Unit,
) {

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        colors = ButtonDefaults
            .buttonColors(
                containerColor = containerColor,
                disabledContainerColor = PrimaryContainer,
            )
    ) {
        TextBold(
            text = stringResource(id = R.string.txt_continue),
            modifier = Modifier.padding(6.dp),
            fontSize = 18.sp,
            color = if (isEnabled) White else Color(0xFF67D1A7)
        )
    }
}


@Preview
@Composable
fun NextButtonPrev() {
    NextButton(
        modifier = Modifier
            .fillMaxWidth(),
        isEnabled = true,
        containerColor = PrimaryColor,
        onClick = {},
    )
}