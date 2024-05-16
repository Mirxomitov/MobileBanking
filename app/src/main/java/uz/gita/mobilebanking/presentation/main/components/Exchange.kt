package uz.gita.mobilebanking.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.buttons.RoundedButton
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.CardColor
import uz.gita.mobilebanking.ui.theme.MainBgLight

@Composable
fun Exchange(
    modifier: Modifier,
    onClickItem: () -> Unit,
    onClickExchange: () -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClickItem
            ),
        colors = CardDefaults.cardColors(containerColor = CardColor),
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_paynet_exchange),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(72.dp)
                )

                Column(
                    modifier
                        .weight(1f)
                        .height(72.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextNormal(text = stringResource(id = R.string.your_level) + " ", fontSize = 12.sp)
                        TextNormal(text = stringResource(id = R.string.starter), fontSize = 12.sp)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextBoldBlack(text = "0 ", modifier = Modifier.padding(end = 4.dp), fontSize = 18.sp)
                        TextNormal(text = stringResource(id = R.string.coin), fontSize = 12.sp)
                    }

                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 1.dp)
                            .background(MainBgLight)
                            .clip(CircleShape),
                        progress = 0f,
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextNormal(text = "100 ", fontSize = 14.sp)
                        TextNormal(text = stringResource(id = R.string.left_after_next_level), fontSize = 12.sp)
                    }
                }
            }

            RoundedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                onClick = { onClickExchange() },
                text = stringResource(id = R.string.exchange)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ExchangePrev() {
    Exchange(
        modifier = Modifier.fillMaxWidth(),
        onClickItem = {},
        onClickExchange = {}
    )
}