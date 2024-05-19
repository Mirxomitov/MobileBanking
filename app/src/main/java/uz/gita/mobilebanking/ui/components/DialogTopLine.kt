package uz.gita.mobilebanking.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import uz.gita.mobilebanking.ui.theme.AuthComponentBg

@Composable
fun DialogTopLine(modifier: Modifier) {
    Box(
        modifier = modifier
            .height(6.dp)
            .width(42.dp)
            .clip(CircleShape)
            .background(AuthComponentBg)
    )
}