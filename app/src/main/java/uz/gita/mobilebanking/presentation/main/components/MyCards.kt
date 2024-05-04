package uz.gita.mobilebanking.presentation.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal

@Composable
fun MyCards(
    modifier: Modifier = Modifier,
    onClickAddCard: () -> Unit
) {
    Card(
        modifier.clickable {
            onClickAddCard()
        }
    ) {
        TextNormal(text = stringResource(id = R.string.my_cards))

        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            TextNormal(
                text = stringResource(id = R.string.add_card),

                )
        }
    }
}