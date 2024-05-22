package uz.gita.mobilebanking.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.model.ui.CardData
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.components.custom_text.TextNormalBlack
import uz.gita.mobilebanking.ui.theme.CardColor
import uz.gita.mobilebanking.ui.theme.MainBgLight
import uz.gita.mobilebanking.ui.theme.PrimaryColor
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.utils.angledGradientBackground
import uz.gita.mobilebanking.utils.toValue

@Composable
fun MyCardsEmpty(
    modifier: Modifier = Modifier, onClickAddCard: () -> Unit,
) {
    Column(
        modifier
            .fillMaxWidth()
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = ShadowColorCard,
            )
            .background(CardColor)
            .padding(8.dp),
    ) {
        TextBoldBlack(
            modifier = Modifier.padding(2.dp),
            text = "Mening kartalarim",
            fontSize = 16.sp
        )

        Column(
            Modifier
                .padding(1.dp)
                .weight(1f)
                .fillMaxWidth()
                .shadow(
                    elevation = 1.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = ShadowColorCard,
                )
                .background(CardColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClickAddCard
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_add_card),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
            )

            TextBoldBlack(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.add_card),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun MyCardsOneCard(
    modifier: Modifier = Modifier, onClickAddCard: () -> Unit, onClickCard: (CardData) -> Unit, card: CardData
) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = ShadowColorCard,
            )
            .background(CardColor)
            .padding(8.dp)
    ) {
        TextBoldBlack(modifier = Modifier.padding(2.dp), text = "Kartalarim", fontSize = 14.sp)

        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .height(200.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClickAddCard
                )
        ) {
            Column(
                modifier = Modifier
                    .height(120.dp)
                    .aspectRatio(ratio = 1.5857725f)
                    .padding(2.dp)
                    .shadow(
                        elevation = 1.dp,
                        shape = RoundedCornerShape(16.dp),
                        ambientColor = ShadowColorCard,
                    )
                    .background(MainBgLight)

            ) {
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    TextNormalBlack(text = stringResource(id = R.string.add_card), fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(40.dp))
            }

            ItemCard(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(120.dp)
                    .aspectRatio(ratio = 1.5857725f),
                cardData = card,
                onClick = { onClickCard(it) }
            )
        }
    }
}


@Composable
fun MyCardsTwoCards(
    modifier: Modifier = Modifier,
    onClickAddCard: () -> Unit,
    onClickFrontCard: () -> Unit,
    onClickBackCard: () -> Unit,
    frontCard: CardData,
    backCard: CardData,
) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = ShadowColorCard,
            )
            .background(CardColor)
            .padding(12.dp)

    ) {
        Box(Modifier.fillMaxWidth()) {
            TextBoldBlack(
                text = "Kartalarim",
                fontSize = 16.sp,
                letterSpacing = 0.8.sp,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(PrimaryColor)
                    .align(Alignment.CenterEnd)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClickAddCard
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                    modifier = Modifier.padding(4.dp),
                    tint = Color.White,
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .height(160.dp)

        ) {
            ItemCard(
                modifier = Modifier
                    .height(96.dp)
                    .fillMaxWidth(),
                cardData = backCard,
                onClick = { onClickBackCard() }
            )

            ItemCard(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(96.dp)
                    .fillMaxWidth(),
                cardData = frontCard,
                onClick = { onClickFrontCard() }
            )
        }
    }
}

@Composable
fun MyCardsMoreCards(
    modifier: Modifier = Modifier,
    onClickShowCards: () -> Unit,
    onClickFrontCard: () -> Unit,
    onClickBackCard: () -> Unit,
    cards: List<CardData>
) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = ShadowColorCard,
            )
            .background(CardColor)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClickShowCards
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextBoldBlack(
                modifier = Modifier.padding(2.dp),
                text = "Kartalarim",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF1A1818)),
                contentAlignment = Alignment.Center
            ) {
                TextNormal(text = "${cards.size}")
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_chevronright),
                contentDescription = null,
                tint = Color(0xFF1A1818),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(24.dp),
            )
        }

        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .height(160.dp)
        ) {
            ItemCard(
                modifier = Modifier
                    .height(96.dp)
                    .fillMaxWidth(), cardData = cards[0],
                onClick = { onClickFrontCard() }
            )

            ItemCard(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(96.dp)
                    .fillMaxWidth(), cardData = cards[1],
                onClick = { onClickBackCard() }
            )
        }

    }
}

@Composable
private fun ItemCard(
    modifier: Modifier = Modifier,
    cardData: CardData,
    onClick: (CardData) -> Unit
) {
    val bankImageID = R.drawable.logo_tbc
    val typeImageID = R.drawable.uzcard_logo
    val money = cardData.amount
    val type = "Uzcard"

    Column(
        modifier = modifier
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = ShadowColorCard,
            )
            .angledGradientBackground(
                colors = listOf(Color(0xFF004103), Color(0xFF4D744E)), degrees = 65f
            )
            .padding(8.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onClick(cardData) }
            ), verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            Modifier.fillMaxWidth()
        ) {

            Image(
                painter = painterResource(id = bankImageID),
                contentDescription = null,
                contentScale = ContentScale.Crop,

                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterStart)
            )

            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(18.dp),
                tint = Color.White,
                painter = painterResource(id = typeImageID),
                contentDescription = "card type",

                )
        }

        Row(
            Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            TextBold(
                text = money.toString().toValue(), fontSize = 14.sp, color = Color.White
            )
            TextBold(
                text = " " + stringResource(id = R.string.som), fontSize = 14.sp, color = Color(0x80FFFFFF)
            )
        }

        Box(
            modifier = Modifier, contentAlignment = Alignment.Center
        ) {
            TextBold(
                text = type,
                fontSize = 14.sp,
                color = Color.White,
            )
        }
    }
}

@Preview
@Composable
fun TwoCardsPreview() {
//    MyCardsTwoCards(
//        modifier = Modifier,
//        onClickAddCard = {},
//        onClickFrontCard = {},
//        onClickBackCard = {},
//        frontCard = CardData(
//
//        ),
//        backCard = CardData(
//
//        ),
//    )
}

@[Composable Preview]
fun MoreCardsPreview() {
//    MyCardsMoreCards(
//        onClickShowCards = {},
//        onClickFrontCard = {},
//        onClickBackCard = {},
//        cards = listOf(
//            CardData("", "", "", "", "", ""),
//            CardData("", "", "", "", "", ""),
//            CardData("", "", "", "", "", ""),
//            CardData("", "", "", "", "", ""),
//        )
//    )
}