package uz.gita.mobilebanking.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
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
import uz.gita.mobilebanking.ui.theme.CardColor

@Composable
fun Support(
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
            .background(CardColor)
            .padding(16.dp),
    ) {
        TextBold(
            text = stringResource(R.string.support),
            fontSize = 16.sp,
            color = textColor
        )

        ItemInfo(
            paddingIcon = 0.dp,
            modifier = Modifier.padding(top = 12.dp),
            icon = R.drawable.ic_operations_comment,
            text = stringResource(R.string.chat_helper)
        )
        ItemInfo(
            paddingIcon = 2.dp,
            modifier = Modifier.padding(top = 12.dp),
            icon = R.drawable.ic_call,
            text = stringResource(R.string.call)
        )
        ItemInfo(
            paddingIcon = 2.dp,
            modifier = Modifier.padding(top = 12.dp),
            icon = R.drawable.write_email,
            text = stringResource(R.string.write_email)
        )
    }
}

@Preview
@Composable
fun SupportPreview() {
    Support()
}