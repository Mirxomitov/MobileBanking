import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.model.ui.CardData
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.CardColor
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.ui.theme.TextColor
import uz.gita.mobilebanking.utils.toValue

@Composable
fun Card(
    modifier: Modifier = Modifier,
    onClickItem: () -> Unit,
    cardData: CardData
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(12.dp), ambientColor = ShadowColorCard)
            .background(CardColor)
            .padding(4.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClickItem
            )
    ) {
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
                TextBoldBlack(text = cardData.pan, fontSize = 18.sp)
            }

            Row(modifier = Modifier.padding(bottom = 4.dp)) {
                TextBoldBlack(text = cardData.amount.toString().toValue() + " ", color = TextColor)
                TextNormal(text = stringResource(id = R.string.som), color = TextColor)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    /*Card(
        modifier = Modifier,
        onClickItem = {},
        cardData = CardData("", "0004", "", "", "Personal", "1000000")
    )*/
}