package uz.gita.mobilebanking.ui.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.components.custom_text.TextNormalBlack
import uz.gita.mobilebanking.ui.theme.cardColor
import uz.gita.mobilebanking.ui.theme.grayColor

data class AddCardDialog(
    val onUzbClick: () -> Unit,
    val onHide: () -> Unit,

    ) : Screen {
    @Composable
    override fun Content() {
        AddCardContent(
            onUzbClick = onUzbClick,
            onHide = onHide
        )
    }
}

@Composable
private fun AddCardContent(
    onUzbClick: () -> Unit,
    onHide: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(cardColor)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .height(6.dp)
                .width(48.dp)
                .clip(CircleShape)
                .background(grayColor)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            TextBoldBlack(
                text = "Qanday kartani qo'shish kerak",
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                modifier = Modifier
                    .size(18.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onHide
                    ),
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null
            )
        }

        // uz
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onUzbClick
                ),
            verticalAlignment = Alignment.CenterVertically,


            ) {
            Image(
                painter = painterResource(id = R.drawable.ic_flag_uz),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .clip(CircleShape)
                    .size(56.dp)
            )

            TextNormalBlack(
                text = stringResource(R.string.uzb_card),
                fontSize = 14.sp,
                letterSpacing = 0.8.sp
            )
        }

        // ru
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { }
                ),
            verticalAlignment = Alignment.CenterVertically,
            ) {
            Image(
                painter = painterResource(id = R.drawable.ic_flag_ru),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .clip(CircleShape)
                    .size(56.dp)
            )

            TextNormalBlack(
                text = stringResource(R.string.card_russia),
                fontSize = 14.sp,
                letterSpacing = 0.8.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }

}

@Preview
@Composable
private fun AddCardPreview() {
    AddCardContent({}, {})
}