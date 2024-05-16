package uz.gita.mobilebanking.presentation.profile.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.GrayIcon

@Composable
fun ItemInfo(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    paddingIcon: Dp = 0.dp,
    text: String,
) {
    val textColor = Color(0xFF1D1D1D)

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = textColor,
            modifier = Modifier
                .size(24.dp)
                .padding(paddingIcon)
        )

        TextNormal(
            text = text,
            modifier = Modifier.padding(start = 8.dp),
            color = textColor
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            painter = painterResource(id = R.drawable.ic_chevronright),
            contentDescription = null,
            tint = GrayIcon,
            modifier = Modifier.size(24.dp)
        )
    }
}