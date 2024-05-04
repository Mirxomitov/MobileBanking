package uz.gita.mobilebanking.ui.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.cardColor
import uz.gita.mobilebanking.ui.theme.textColor

@Composable
fun CardP2PSendItem(
    modifier: Modifier, onClickItem: () -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        modifier = modifier.clickable { onClickItem() },
        colors = CardDefaults.cardColors(containerColor = cardColor),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
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
                Row(modifier = Modifier.padding(vertical = 4.dp)) {
                    TextBoldBlack(text = "Uzcard", fontSize = 18.sp)
                    TextBoldBlack(text = " * ", fontSize = 18.sp)
                    TextBoldBlack(text = "1137", fontSize = 18.sp)
                }

                Row(modifier = Modifier.padding(bottom = 4.dp)) {
                    TextBoldBlack(text = "142 557.45", color = textColor)
                    TextNormal(text = stringResource(id = R.string.som), color = textColor)
                }
            }

            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_chevronright),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CardP2PSendItem(
        modifier = Modifier
    ) {

    }
}