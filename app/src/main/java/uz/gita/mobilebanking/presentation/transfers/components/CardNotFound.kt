package uz.gita.mobilebanking.presentation.transfers.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.CardColor
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.ui.theme.TextColorLight

@Composable
fun CardNotFound(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = ShadowColorCard
            )
            .background(CardColor)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.provider_not_found),
            contentDescription = null,
            modifier = Modifier
                .height(96.dp)
        )

        TextBoldBlack(
            modifier = Modifier.padding(top = 4.dp),
            text = stringResource(R.string.we_cant_found_such_card),
            fontSize = 24.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        TextNormal(
            modifier = Modifier.padding(top = 4.dp),
            text = stringResource(R.string.may_be_number_is_not_correct),
            fontSize = 14.sp,
            color = TextColorLight,
            textAlign = TextAlign.Center
        )
    }
}
