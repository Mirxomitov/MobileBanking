package uz.gita.mobilebanking.ui.components.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.theme.onPrimaryContainer
import uz.gita.mobilebanking.ui.theme.primaryColor
import uz.gita.mobilebanking.ui.theme.primaryContainer

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
                contentColor = Color.White,
                disabledContainerColor = primaryContainer,
                disabledContentColor = onPrimaryContainer
            )
    ) {
        TextBold(
            text = stringResource(id = R.string.txt_continue),
            modifier = Modifier.padding(6.dp),
            fontSize = 18.sp,
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
        containerColor = primaryColor,
        onClick = {},
    )
}