package uz.gita.mobilebanking.ui.dialogs

import Card
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.model.ui.CardData
import uz.gita.mobilebanking.ui.components.DialogTopLine
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.theme.CardColor
import uz.gita.mobilebanking.ui.theme.GrayColor
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme

data class ChangeCard(
    val cards: List<CardData>,
    val onClickCard: (CardData) -> Unit,
    val onClickAddCard: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        ChangeCardContent(cards, onClickCard, onClickAddCard)
    }
}

@Composable
fun ChangeCardContent(
    cards: List<CardData>,
    onClickCard: (CardData) -> Unit,
    onClickAddCard: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardColor)
            .padding(12.dp)
    ) {
        DialogTopLine(modifier = Modifier.align(Alignment.CenterHorizontally))

        TextBold(
            text = stringResource(id = R.string.cards),
            fontSize = 18.sp,
            color = GrayColor
        )

        cards.forEach { card ->
            Card(
                modifier = Modifier.padding(top = 12.dp),
                onClickItem = { onClickCard(card) },
                cardData = card
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onClickAddCard,
            Modifier.fillMaxWidth(),
            border = BorderStroke(width = 1.2.dp, color = Color.Black)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_black),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
                    .align(Alignment.CenterVertically),
                tint = Color.Black
            )

            TextBoldBlack(
                text = stringResource(id = R.string.add_card),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
fun ChangeCardPreview() {
    MobileBankingTheme {
        ChangeCardContent(
            cards = listOf(
                CardData("", "0003", "", "", "Personal", "66600"),
                CardData("", "0004", "", "", "Personal", "1000000")
            ),
            onClickAddCard = {},
            onClickCard = {},
        )
    }
}