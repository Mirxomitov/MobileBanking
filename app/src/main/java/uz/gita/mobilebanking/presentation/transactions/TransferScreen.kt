package uz.gita.mobilebanking.presentation.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.presentation.transactions.components.AddTemplate
import uz.gita.mobilebanking.presentation.transactions.components.ItemSelfTransfer
import uz.gita.mobilebanking.presentation.transactions.components.LastPayedCards
import uz.gita.mobilebanking.presentation.transactions.components.SearchBar
import uz.gita.mobilebanking.presentation.transactions.components.Template
import uz.gita.mobilebanking.presentation.transactions.components.TransferToCountry
import uz.gita.mobilebanking.ui.components.TopBar
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.ui.theme.cardColor
import uz.gita.mobilebanking.ui.theme.toRussiaColor
import uz.gita.mobilebanking.ui.theme.toUzbColor
import uz.gita.mobilebanking.ui.theme.transactionItemColor
import uz.gita.mobilebanking.ui.theme.transactionItemColor2

class TransfersScreen : Screen {
    @Composable
    override fun Content() {
        MobileBankingTheme {
            val viewModel: TransferContract.Model = getViewModel<TransferModel>()

            TransactionsScreenContent(
                viewModel::onEventDispatchers
            )
        }
    }
}

@Composable
private fun TransactionsScreenContent(
    onEventDispatchers: (TransferContract.Intent) -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = cardColor)
            .verticalScroll(rememberScrollState()),
    ) {
        TopBar(
            modifier = Modifier
                .padding(vertical = 28.dp, horizontal = 12.dp),
            stringResource(id = R.string.transactions),
        )

        SearchBar(
            Modifier.padding(horizontal = 12.dp),
            stringResource(id = R.string.card_or_phone),
            onClickContacts = {},
            onClickScan = {}
        )

        LazyRow(
            contentPadding = PaddingValues(start = 12.dp),
            modifier = Modifier
                .padding(top = 24.dp)
        ) {
            items(10) {
                LastPayedCards(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    imageID = R.drawable.logo_tbc,
                    firstName = "Tohir",
                    lastName = "Mirxomitov",
                    onClick = {
                        onEventDispatchers(TransferContract.Intent.ToP2PScreen)
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
                modifier = Modifier
                    .weight(1f),
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
            modifier = Modifier
                .padding(top = 12.dp),
            contentPadding = PaddingValues(start = 12.dp),
        ) {
            item {
                AddTemplate(
                    Modifier
                        .padding(end = 24.dp),
                    onClick = {}
                )
            }
            items(10) {
                Template(
                    modifier = Modifier.padding(end = 24.dp),
                    firstName = "Saidrasul",
                    imageID = R.drawable.logo_tbc
                )
            }
        }

        TextBoldBlack(
            text = stringResource(R.string.international_transactions),
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 36.dp, start = 12.dp)
        )

        Row(modifier = Modifier.padding(top = 24.dp, start = 12.dp)) {
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

        Spacer(modifier = Modifier.height(60.dp))
    }
}


@Preview(showBackground = true, device = "id:pixel_7_pro")
@Composable
fun TransactionsScreenPreview() {
    TransactionsScreenContent({})
}
