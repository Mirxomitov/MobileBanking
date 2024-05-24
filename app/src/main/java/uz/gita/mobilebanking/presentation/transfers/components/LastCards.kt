package uz.gita.mobilebanking.presentation.transfers.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.gita.mobilebanking.data.model.PayedCardData
import uz.gita.mobilebanking.ui.components.cards.CardP2PWithCardNumber

@Composable
fun LastCards(
    modifier: Modifier = Modifier,
    payedCards: List<PayedCardData>,
    onClickCard: (PayedCardData) -> Unit
) {
    Column(modifier) {
        LazyColumn() {
            items(payedCards) { card ->
                CardP2PWithCardNumber(
                    modifier = Modifier.padding(top = 12.dp),
                    cardNumber = card.pan,
                    ownerName = card.ownerName,
                    onClickItem = { onClickCard(card) }
                )
            }
        }
    }
}
