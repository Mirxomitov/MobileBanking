package uz.gita.mobilebanking.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.ui.theme.cardColor
import uz.gita.mobilebanking.ui.theme.primaryColor
import uz.gita.mobilebanking.ui.theme.textColor

@Composable
fun CashBack(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(cardColor)
            .padding(12.dp)
    ) {
        TextNormal(fontSize = 18.sp, text = stringResource(R.string.cash_back_calculation), color = Black)

        TextNormal(
            text = stringResource(id = R.string.balance),
            modifier = Modifier.padding(top = 4.dp),
            color = textColor
        )

        Row {
            TextBold(
                text = "3 444", fontSize = 18.sp,
                color = Black
            )

            TextBold(
                text = "\t" + stringResource(id = R.string.som), fontSize = 18.sp, color = textColor
            )
        }

        Card(
            Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(12.dp),
                    ambientColor = ShadowColorCard
                )
        ) {
            Column(
                Modifier
                    .background(White)
                    .padding(12.dp)
            ) {

                Row(Modifier, verticalAlignment = Alignment.CenterVertically) {
                    TextNormal(text = "Bugun", color = textColor, fontSize = 12.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        Modifier
                            .padding(2.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(primaryColor)
                            .padding(2.dp)
                    ) {
                        TextBold(text = "0")
                        TextBold(text = "\t" + stringResource(id = R.string.som))
                    }
                }

                Row() {
                    repeat(7) {

                        val x = (it + 1) / 10f
                        Column(Modifier.height(100.dp)) {
                            Box(
                                modifier = Modifier
                                    .weight(x)
                            )
                            Box(
                                modifier = Modifier
                                    .weight(1 - x)
                                    .background(Gray)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun CashBackPreview() {
    CashBack()
}