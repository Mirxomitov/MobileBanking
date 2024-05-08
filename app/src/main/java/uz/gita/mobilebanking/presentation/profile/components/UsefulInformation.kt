package uz.gita.mobilebanking.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.ui.theme.cardColor

@Composable
fun UsefulInformation(
    modifier: Modifier = Modifier,
    onClickMap: () -> Unit
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
            text = stringResource(R.string.useful_information),
            fontSize = 16.sp,
            color = textColor,
        )

        ItemInfo(
            paddingIcon = 2.dp,
            modifier = Modifier.padding(top = 12.dp),
            icon = R.drawable.ic_info,
            text = stringResource(R.string.about_paynet),
        )
        ItemInfo(
            paddingIcon = 2.dp,
            modifier = Modifier.padding(top = 12.dp),
            icon = R.drawable.ic_question,
            text = stringResource(R.string.reference)
        )

        ItemInfo(
            paddingIcon = 2.dp,
            modifier = Modifier
                .padding(top = 12.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClickMap
                ),
            icon = R.drawable.ic_location,
            text = stringResource(R.string.we_on_map),
        )
    }
}


@Preview
@Composable
fun UsefulInformationPreview() {
    UsefulInformation(Modifier, {})
}