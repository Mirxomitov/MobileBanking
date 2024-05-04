package uz.gita.mobilebanking.presentation.main.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.cardColor
import uz.gita.mobilebanking.ui.theme.primaryColor

@Composable
fun FillTransactPay(
    modifier: Modifier,
    balance: String,
    cardNumber: String,
    onClickWhatIsIt: () -> Unit,
    onClickItem: () -> Unit,
    onClickFill: () -> Unit,
    onClickTransact: () -> Unit,
    onClickPay: () -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        modifier = modifier.clickable { onClickItem() },
        colors = CardDefaults.cardColors(containerColor = cardColor),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp, start = 4.dp, end = 4.dp)
        ) {
            // karta bu nima
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                TextBoldBlack(
                    text = stringResource(id = R.string.pay_net_card),
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.weight(1f))

                TextBoldBlack(
                    text = stringResource(id = R.string.what_is_it),
                    modifier = Modifier,
                    color = primaryColor,
                    textAlign = TextAlign.Center,
                )
            }
            // karta rasmi
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_paynet),
                    contentDescription = "pay net card",
                    modifier = Modifier
                        .width(72.dp)
                        .padding(end = 12.dp)
                )

                Column(modifier = Modifier.weight(1f)) {
                    Row {
                        TextNormal(text = stringResource(id = R.string.pay_net_card) + " * ")
                        TextNormal(text = cardNumber)
                    }

                    Row {
                        TextBoldBlack(text = balance, fontSize = 14.sp)
                        TextBold(text = " " + stringResource(id = R.string.som), fontSize = 14.sp)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Item(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    icon = R.drawable.ic_add_black,
                    text = stringResource(id = R.string.fill),
                    onClick = {
                        onClickFill()
                    })

                Item(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.ic_add_black,
                    text = stringResource(id = R.string.transact),
                    onClick = {
                        onClickTransact()
                    })

                Item(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    icon = R.drawable.ic_add_black,
                    text = stringResource(id = R.string.pay),
                    onClick = {
                        onClickPay()
                    })
            }
        }
    }
}


@Composable
private fun Item(
    modifier: Modifier,
    @DrawableRes icon: Int,
    text: String,
    onClick: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(id = icon), contentDescription = null, modifier = Modifier.size(32.dp)
            )

            TextBoldBlack(text = text, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
        }
    }
}

