package uz.gita.mobilebanking.ui.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.theme.CardColor
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.ui.theme.TextColor

@Composable
fun CardP2PWithCardNumber(
    modifier: Modifier = Modifier,
    onClickItem: () -> Unit,
    cardNumber: String,
    ownerName: String,
) {

    Row(
        modifier = modifier
            .shadow(
                elevation = 1.dp, shape = RoundedCornerShape(12.dp), ambientColor = ShadowColorCard
            )
            .background(CardColor)
            .padding(4.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClickItem
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.paynet),
            contentDescription = null,
            modifier = Modifier
                .height(36.dp)
                .width(56.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 2.dp)
        ) {
            TextBoldBlack(
                modifier = Modifier.padding(vertical = 4.dp),
                text = ownerName,
                fontSize = 18.sp,
                overflow = TextOverflow.Ellipsis
            )

            TextBoldBlack(
                modifier = Modifier.padding(bottom = 4.dp),
                text = formatCardNumber(cardNumber),
                color = TextColor
            )
        }

        Icon(
            modifier = Modifier.padding(end = 12.dp),
            painter = painterResource(id = R.drawable.ic_chevronright),
            contentDescription = null
        )

    }
}

private fun formatCardNumber(cardNumber: String): String {
    return try {
        cardNumber.substring(0, 4) + " " + cardNumber.substring(4, 6) + "** **** " + cardNumber.substring(12, 16)
    } catch (e: StringIndexOutOfBoundsException) {
        "**** **** **** ****"
    } catch (e: Exception) {
        ""
    }
}

@Preview
@Composable
private fun Preview() {
    CardP2PWithCardNumber(
        modifier = Modifier,
        onClickItem = {},
        cardNumber = "0008000800080001",
        ownerName = "Tohir Mirxomitov"
    )
}

