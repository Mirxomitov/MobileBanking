package uz.gita.mobilebanking.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.theme.AuthComponentBg

@Composable
fun Flag(
    modifier: Modifier,
    @DrawableRes flagIcon: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(AuthComponentBg)
            .clickable {
                onClick()
            }
    ) {

        Row(
            modifier = Modifier
                .padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            /* Add your row content here */
            TextBoldBlack(
                text = stringResource(id = R.string.language),
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp),
                textAlign = TextAlign.Center
            )

            Image(
                painter = painterResource(id = flagIcon),
                contentDescription = null,
                modifier = Modifier
                    .height(32.dp)
                    .padding(end = 4.dp)
                    .width(56.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Preview
@Composable
fun FlagPreview() {
    Flag(
        modifier = Modifier
            .wrapContentHeight()
            .padding(8.dp),
        flagIcon = R.drawable.ic_flag_uz,
        onClick = {}
    )
}