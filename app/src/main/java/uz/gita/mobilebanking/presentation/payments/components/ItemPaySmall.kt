package uz.gita.mobilebanking.presentation.payments.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.ui.theme.CardColor
import uz.gita.mobilebanking.ui.theme.textColor
import uz.gita.mobilebanking.ui.theme.TextColorLight

@Composable
fun ItemPaySmall(
    modifier: Modifier = Modifier,
    @DrawableRes imageID: Int,
    mainTitle: String,
    secondTitle: String
) {
    Row(
        modifier = modifier
            .shadow(
                elevation = 1.dp, ambientColor = ShadowColorCard, shape = RoundedCornerShape(16.dp)
            )
            .background(CardColor)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(36.dp)
                .padding(4.dp),
            painter = painterResource(id = imageID),
            contentDescription = null
        )

        Column(
            modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center
        ) {
            TextBoldBlack(
                text = mainTitle, fontSize = 18.sp, color = Color.Black
            )
            TextNormal(
                modifier = Modifier.padding(top = 4.dp),
                text = secondTitle,
                fontSize = 14.sp,
                color = TextColorLight
            )
        }

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF63D18C))
                .padding(4.dp)
        ) {
            TextNormal(color = textColor, fontSize = 14.sp, text = "2.2")
            TextNormal(color = textColor, fontSize = 14.sp, text = "%")
        }
    }
}