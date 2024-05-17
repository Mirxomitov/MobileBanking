package uz.gita.mobilebanking.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    ) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(1.dp, Color.Black),
        shape = CircleShape,
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
    ) {
        TextBoldBlack(modifier = Modifier.padding(2.dp), text = text, fontSize = 18.sp)
    }
}

@Preview
@Composable
fun RoundedButtonPreview() {
    MobileBankingTheme {
        RoundedButton(modifier = Modifier, onClick = { }, text = "Hello")
    }
}