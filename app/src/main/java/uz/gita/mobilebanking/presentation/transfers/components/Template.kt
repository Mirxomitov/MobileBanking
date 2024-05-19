package uz.gita.mobilebanking.presentation.transfers.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.AuthComponentBg
import uz.gita.mobilebanking.ui.theme.textColor

@Composable
fun Template(
    modifier: Modifier = Modifier,
    @DrawableRes imageID: Int,
    firstName: String,
) {
    Column(
        modifier = modifier
            .width(60.dp)
    ) {
        Image(
            painter = painterResource(id = imageID),
            contentDescription = null,
            contentScale = ContentScale.Crop,

            modifier = Modifier
                .size(60.dp)
                .border(
                    BorderStroke(2.dp, AuthComponentBg), CircleShape
                )
                .padding(1.dp)
                .clip(CircleShape)
        )

        Spacer(
            modifier = Modifier
                .height(4.dp)
        )

        TextNormal(
            text = firstName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textColor,
            fontSize = 12.sp,
            letterSpacing = 0.8.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview
@Composable
fun Preview() {
    Template(imageID = R.drawable.logo_tbc, firstName = "Tohir")
}