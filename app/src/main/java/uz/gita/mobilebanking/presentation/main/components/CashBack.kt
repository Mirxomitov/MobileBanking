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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.CardColor
import uz.gita.mobilebanking.ui.theme.PrimaryColor
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.ui.theme.GrayColor
import uz.gita.mobilebanking.ui.theme.TextColor

@Composable
fun CashBack(
    modifier: Modifier = Modifier,
    isVisibleMoney: Boolean
) {
    Column(
        modifier = modifier
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(16.dp), ambientColor = ShadowColorCard)
            .background(CardColor)
            .padding(12.dp)

    ) {
        TextBold(
            fontSize = 16.sp,
            text = stringResource(R.string.cash_back_calculation),
            color = Black
        )

        TextNormal(
            text = stringResource(id = R.string.balance),
            modifier = Modifier.padding(top = 8.dp),
            color = TextColor,
            fontSize = 14.sp
        )

        Row {
            TextBold(
                text = if (isVisibleMoney) "3 444" else "• •••",
                fontSize = 18.sp,
                color = Black
            )

            TextBold(
                text = "\t" + stringResource(id = R.string.som), fontSize = 18.sp, color = TextColor
            )
        }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .shadow(
                    elevation = 1.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = ShadowColorCard
                )
                .background(White)
                .padding(4.dp)
        ) {

            Row(Modifier, verticalAlignment = Alignment.CenterVertically) {
                TextNormal(text = "Bugun", color = TextColor, fontSize = 12.sp)
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(PrimaryColor)
                        .padding(2.dp)
                ) {
                    TextBold(color = White, text = if (isVisibleMoney) "0" else "• •••", fontSize = 12.sp)
                    TextBold(color = White, text = "\t" + stringResource(id = R.string.som), fontSize = 12.sp)
                }
            }

            Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                repeat(7) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .padding(horizontal = 1.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(10f)
                                .fillMaxWidth()

                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(horizontal = 1.dp)
                                .clip(
                                    RoundedCornerShape(4.dp)
                                )
                                .background(GrayColor)
                        )

                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun CashBackPreview() {
    CashBack(isVisibleMoney = true)
}