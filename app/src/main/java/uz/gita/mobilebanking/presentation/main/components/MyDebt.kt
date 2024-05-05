package uz.gita.mobilebanking.presentation.main.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.ui.theme.cardColor
import uz.gita.mobilebanking.ui.theme.mainBgLight
import uz.gita.mobilebanking.ui.theme.textColorLight

@Composable
fun MyDebt(modifier: Modifier = Modifier) {
    Column(
        modifier
            .shadow(
                elevation = 1.dp, ambientColor = ShadowColorCard, shape = RoundedCornerShape(16.dp)
            )
            .background(cardColor)
            .padding(12.dp)
    ) {
        TextBold(
            text = stringResource(R.string.my_debt),
            fontSize = 18.sp,
            color = Color.Black
        )

        Item(
            modifier = Modifier.padding(top = 12.dp),
            image = R.drawable.ic_merchants_soliq,
            title = stringResource(id = R.string.the_state_tax_committee),
            state = stringResource(id = R.string.no_debt)
        )

        Item(
            modifier = Modifier.padding(top = 12.dp),
            image = R.drawable.ic_merchants_mib,
            title = stringResource(R.string.mib_debt),
            state = stringResource(id = R.string.no_debt)
        )
    }
}

@Composable
private fun Item(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    title: String,
    state: String,
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TextBold(
                text = title,
                fontSize = 14.sp,
                color = Color.Black,
                letterSpacing = 0.8.sp,
            )

            TextNormal(
                modifier = Modifier.padding(top = 2.dp),
                text = state,
                color = textColorLight,
                fontSize = 12.sp
            )
        }
    }
}

@Preview
@Composable
fun MyDebtPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(mainBgLight),
    ) {
        MyDebt()
    }
}