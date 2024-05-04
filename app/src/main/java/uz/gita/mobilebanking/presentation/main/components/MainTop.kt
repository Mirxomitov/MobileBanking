package uz.gita.mobilebanking.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.theme.primaryColor

@Composable
fun MainTop(
    modifier: Modifier,
    userName: String,
    onClickItem: () -> Unit,
) {

    Row(
        modifier = modifier.clickable { onClickItem() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(primaryColor, shape = CircleShape),
        )

        TextBoldBlack(
            modifier = Modifier.padding(start = 12.dp),
            text = userName,
            fontSize = 14.sp
        )

        Icon(
            painter = rememberVectorPainter(
                image = Icons.Default.KeyboardArrowRight
            ),
            contentDescription = "right"
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_1),
            contentDescription = null
        )

        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_2),
            contentDescription = null
        )
    }
}
