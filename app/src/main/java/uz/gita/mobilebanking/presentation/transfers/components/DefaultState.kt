package uz.gita.mobilebanking.presentation.transfers.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.model.PayedCardData
import uz.gita.mobilebanking.data.model.TemplateCardData
import uz.gita.mobilebanking.data.model.TemplateData
import uz.gita.mobilebanking.presentation.transfers.TransferContract
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.theme.CardColor
import uz.gita.mobilebanking.ui.theme.toRussiaColor
import uz.gita.mobilebanking.ui.theme.toUzbColor
import uz.gita.mobilebanking.ui.theme.transactionItemColor
import uz.gita.mobilebanking.ui.theme.transactionItemColor2
import uz.gita.mobilebanking.utils.Constants

@Composable
fun DefaultState(
    lastPayedCards: List<PayedCardData>,
    cardTemplates: List<TemplateCardData>,
    onEventDispatcher: (TransferContract.Intent) -> Unit
) {
    Column(Modifier.verticalScroll(rememberScrollState())) {

        LazyRow(
            contentPadding = PaddingValues(start = 12.dp),
            modifier = Modifier
                .background(CardColor)
                .padding(top = 24.dp)
        ) {
             items(lastPayedCards) { card ->
                LastPayedCards(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    imageID = R.drawable.logo_tbc,
                    firstName = card.ownerName,
                    lastName = "",
                    onClick = {
                        onEventDispatcher(
                            TransferContract.Intent.ToP2PScreen(
                                card.pan,
                                card.ownerName
                            )
                        )
                    }
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
                AddTemplate(
                    Modifier.padding(end = 24.dp),
                    onClick = {
                        onEventDispatcher(TransferContract.Intent.AddCardTemplate)
                    }
                )
            }
            items(cardTemplates) { data ->
                Template(
                    modifier = Modifier.padding(end = 24.dp),
                    data = data,
                )
            }
        }

        TextBoldBlack(
            text = stringResource(R.string.international_transactions),
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 36.dp, start = 12.dp)
        )

        Row(
            modifier = Modifier
                .padding(top = 24.dp)
                .padding(horizontal = 12.dp)
        ) {
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

        Spacer(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .height(Constants.BOTTOM_NAVIGATION_HEIGHT.dp)
        )
    }
}