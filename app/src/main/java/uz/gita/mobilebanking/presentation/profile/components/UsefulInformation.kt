package uz.gita.mobilebanking.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.ui.theme.cardColor

@Composable
fun UsefulInformation(
    modifier: Modifier = Modifier
) {
    val textColor = Color(0xFF1D1D1D)

    Column(
        modifier = modifier
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = ShadowColorCard
            )
            .background(cardColor)
            .padding(16.dp),
    ) {
        TextBold(
            text = stringResource(R.string.useful_information), fontSize = 14.sp,
            color = textColor
        )

        ItemInfo(
            modifier = Modifier.padding(top = 12.dp),
            icon = R.drawable.ic_info,
            text = "Paynet haqida",
        )
        ItemInfo(
            modifier = Modifier.padding(top = 12.dp),
            icon = R.drawable.ic_question,
            text = "Ma'lumotnoma"
        )
    }
}



@Preview
@Composable
fun UsefulInformationPreview() {
    UsefulInformation()
}