package uz.gita.mobilebanking.presentation.my_cards.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.model.CardData
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.utils.getGradient
import uz.gita.mobilebanking.utils.toCardExpirationDate
import uz.gita.mobilebanking.utils.toValue


@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    cardData: CardData,
    onClickCard: (CardData) -> Unit
) {
    val typeImageID = R.drawable.ic_paymentsystem_humo
    val money = cardData.amount.toString()
    val type = "Humo"
    val selectedColor = getGradient(cardData.themeType)

    Column(
        modifier = modifier
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = ShadowColorCard,
            )
            .background(
                brush = selectedColor,
                shape = RoundedCornerShape(12.dp),
            )
//            .angledGradientBackground(
//                colors = listOf(Color(0xFF004103), Color(0xFF4D744E)), degrees = 65f
//            )
            .padding(8.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onClickCard(cardData) }
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Icon(
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 4.dp)
                .size(56.dp),
            tint = Color.White,
            painter = painterResource(id = typeImageID),
            contentDescription = "card type"
        )

        Column(
            Modifier.fillMaxWidth(),
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextBold(
                    text = money.toValue(), fontSize = 24.sp, color = Color.White, letterSpacing = 0.8.sp
                )
                TextBold(
                    text = " " + stringResource(id = R.string.som),
                    fontSize = 20.sp,
                    color = Color(0x80FFFFFF),
                    letterSpacing = 0.8.sp
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                TextNormal(
                    text = "**** **** **** ${cardData.pan}",
                    fontSize = 16.sp,
                    color = Color.White,
                    letterSpacing = 0.8.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                TextNormal(text = cardData.toCardExpirationDate(), fontSize = 14.sp, color = Color.White)
            }
        }

        Box(
            modifier = Modifier, contentAlignment = Alignment.Center
        ) {
            TextNormal(
                text = type,
                fontSize = 18.sp,
                color = Color.White,
            )
        }
    }
}

@Preview
@Composable
fun ItemCardPreview() {
    //ItemCard(cardData = CardData("", "0005", "2028", "6", "Personal", "10000000"))
}