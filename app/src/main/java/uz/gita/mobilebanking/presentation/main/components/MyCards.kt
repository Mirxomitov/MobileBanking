package uz.gita.mobilebanking.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.ui.theme.cardColor

@Composable
fun MyCards(
    modifier: Modifier = Modifier,
    onClickAddCard: () -> Unit
) {
    Column(
        modifier
            .clickable {
                onClickAddCard()
            }
            .shadow(
                elevation = 2.dp,
                ambientColor = ShadowColorCard,
                shape = RoundedCornerShape(16.dp)
            )
            .background(cardColor)
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