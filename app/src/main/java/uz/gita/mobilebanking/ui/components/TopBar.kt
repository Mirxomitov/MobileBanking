package uz.gita.mobilebanking.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack

@Composable
fun TopBar(
    modifier: Modifier,
    title: String,
    @DrawableRes icon: Int? = null,
    onClickIcon: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
    ) {
        TextBoldBlack(text = title, fontSize = 28.sp)

        Spacer(modifier = Modifier.weight(1f))

        if (icon != null) {
            IconButton(
                onClick = {
                    onClickIcon?.invoke()
                },
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null
                )
            }
        }
    }
}
