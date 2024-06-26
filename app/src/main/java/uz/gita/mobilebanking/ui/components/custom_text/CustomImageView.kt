package uz.gita.mobilebanking.ui.components.custom_text

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun CustomImageView(
    src: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = src),
        contentDescription = null
    )
}