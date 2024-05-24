
package uz.gita.mobilebanking.presentation.history.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.model.HistoryChildData
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.TextColor
import uz.gita.mobilebanking.utils.toValue

@Composable
fun HistoryListItem(
    modifier: Modifier = Modifier,
    transferHistoryChild: HistoryChildData
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.ic_add_black),
            contentDescription = null,
            Modifier.size(56.dp),
        )

        Column(modifier = Modifier.weight(5f), verticalArrangement = Arrangement.SpaceBetween) {
            TextBold(
                text = transferHistoryChild.to.uppercase(),
                fontSize = 18.sp,
                color = Color.Black,
                overflow = TextOverflow.Clip
            )
            TextNormal(text = transferHistoryChild.type, fontSize = 12.sp, color = TextColor)
        }

        Column(modifier = Modifier.weight(2f), verticalArrangement = Arrangement.SpaceBetween) {
            TextBold(
                text = "${transferHistoryChild.amount.toString().toValue()} " + stringResource(id = R.string.som),
                fontSize = 18.sp
            )
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_add_card),
                    contentDescription = null,
                    tint = TextColor
                )
                TextNormal(text = "* 1137", fontSize = 8.sp, color = TextColor)
            }
        }
    }
}