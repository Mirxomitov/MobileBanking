package uz.gita.mobilebanking.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack

@Composable
fun TopBarWithBack(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int = R.drawable.ic_back,
    title: String,
    onClickIcon: () -> Unit
) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onClickIcon) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = icon),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        TextBoldBlack(
            text = title,
            fontSize = 20.sp,
        )
    }
}

@Preview
@Composable
private fun TopBarWithBackPreview() {
    TopBarWithBack(
        modifier = Modifier.fillMaxWidth(),
        icon = R.drawable.ic_back,
        title = stringResource(R.string.transfer_to_card),
        onClickIcon = {}
    )
}