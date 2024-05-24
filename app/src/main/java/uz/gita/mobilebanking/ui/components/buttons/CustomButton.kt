package uz.gita.mobilebanking.ui.components.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import uz.gita.mobilebanking.ui.components.custom_text.CustomTextView

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int,
    fontWeight: Int,
    textColor: Color,
    colors: ButtonColors,
    shape: Shape = RoundedCornerShape(100),
    enabled: Boolean = true,
    click: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = colors,
        shape = shape,
        enabled = enabled,
        onClick = click
    ) {

        CustomTextView(
            text = text,
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = textColor
        )
    }
}