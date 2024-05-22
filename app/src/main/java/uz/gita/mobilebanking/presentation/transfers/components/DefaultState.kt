package uz.gita.mobilebanking.presentation.transfers.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.theme.CardColor
import uz.gita.mobilebanking.ui.theme.toRussiaColor
import uz.gita.mobilebanking.ui.theme.toUzbColor
import uz.gita.mobilebanking.ui.theme.transactionItemColor
import uz.gita.mobilebanking.ui.theme.transactionItemColor2

@Composable
fun DefaultState(
    onClickLastPayedCard: () -> Unit,
) {
    LazyRow(
        contentPadding = PaddingValues(start = 12.dp), modifier = Modifier
            .background(CardColor)
            .padding(top = 24.dp)
    ) {
        items(0) {
            LastPayedCards(
                modifier = Modifier.padding(horizontal = 8.dp),
                imageID = R.drawable.logo_tbc,
                firstName = "Tohir",
                lastName = "Mirxomitov",
                onClick = onClickLastPayedCard
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 12.dp, end = 12.dp)
    ) {
        ItemSelfTransfer(
            modifier = Modifier
                .padding(end = 12.dp)
                .weight(1f),
            title = stringResource(R.string.to_my_card),
            iconID = R.drawable.self_transfer_to_card,
            bgColor = transactionItemColor
        )

        ItemSelfTransfer(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.to_my_wallet),
            iconID = R.drawable.self_transfer_to_wallet,
            bgColor = transactionItemColor2
        )
    }

    TextBoldBlack(
        text = stringResource(R.string.templates),
        fontSize = 18.sp,
        modifier = Modifier.padding(top = 24.dp, start = 12.dp)
    )

    LazyRow(
        modifier = Modifier.padding(top = 12.dp),
        contentPadding = PaddingValues(start = 12.dp),
    ) {
        item {
            AddTemplate(Modifier.padding(end = 24.dp), onClick = {})
        }
        items(0) {
            Template(
                modifier = Modifier.padding(end = 24.dp), firstName = "Saidrasul", imageID = R.drawable.logo_tbc
            )
        }
    }

    TextBoldBlack(
        text = stringResource(R.string.international_transactions),
        fontSize = 18.sp,
        modifier = Modifier.padding(top = 36.dp, start = 12.dp)
    )

    Row(modifier = Modifier.padding(top = 24.dp).padding(horizontal = 12.dp)) {
        TransferToCountry(
            modifier = Modifier
                .weight(1f)
                .padding(end = 12.dp),
            backgroundColor = toRussiaColor,
            title = stringResource(R.string.to_russia),
            icon = R.drawable.uzb_to_ru
        )

        TransferToCountry(
            modifier = Modifier.weight(1f),
            backgroundColor = toUzbColor,
            title = stringResource(R.string.to_uzbekistan),
            icon = R.drawable.ru_to_uzb
        )
    }
}
