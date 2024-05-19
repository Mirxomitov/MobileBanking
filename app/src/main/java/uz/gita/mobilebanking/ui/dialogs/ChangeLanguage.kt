package uz.gita.mobilebanking.ui.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.theme.AuthComponentBg
import uz.gita.mobilebanking.ui.theme.CardColor
import uz.gita.mobilebanking.ui.theme.PrimaryColor
import uz.gita.mobilebanking.ui.theme.textColor

data class ChangeLanguage(
    val isUzbekEnabled: Boolean,
    val onClick: (Boolean) -> Unit,
    val dismiss: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        ChangeLanguageDialog(
            isUzbekEnabled = isUzbekEnabled,
            onClick = onClick,
            dismiss = dismiss
        )
    }
}

@Composable
private fun ChangeLanguageDialog(
    modifier: Modifier = Modifier,
    isUzbekEnabled: Boolean = true,
    onClick: (Boolean) -> Unit,
    dismiss: () -> Unit
) {
    Column(
        modifier
            .fillMaxWidth()
            .background(CardColor)
            .padding(12.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .height(6.dp)
                .width(48.dp)
                .clip(CircleShape)
                .background(AuthComponentBg)
                .align(Alignment.CenterHorizontally)
        )

        Box(
            modifier = Modifier
                .align(Alignment.End)
                .size(32.dp)
                .clip(CircleShape)
                .background(AuthComponentBg)
                .clickable { dismiss() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                Modifier
                    .size(12.dp)
                    .align(Alignment.Center),
                tint = textColor
            )
        }

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 14.dp),
            text = stringResource(R.string.application_language),
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_semibold))
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(
                    if (!isUzbekEnabled) Color.White else Color.Transparent
                )
                .clickable {
                    onClick(false)
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_flag_ru),
                contentDescription = null,
                Modifier
                    .width(56.dp)
                    .height(24.dp)
            )
            TextBoldBlack(
                modifier = Modifier.padding(start = 8.dp), fontSize = 18.sp, text = stringResource(R.string.ru_russian)
            )

            Spacer(modifier = Modifier.weight(1f))

            if (!isUzbekEnabled) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null,
                    tint = PrimaryColor,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(
                    if (isUzbekEnabled) Color.White else Color.Transparent
                )
                .clickable {
                    onClick(true)
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_flag_uz),
                contentDescription = null,
                Modifier
                    .width(56.dp)
                    .height(24.dp)
            )
            TextBoldBlack(
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 18.sp,
                text = stringResource(R.string.uzbek)
            )

            Spacer(modifier = Modifier.weight(1f))

            if (isUzbekEnabled) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null,
                    tint = PrimaryColor,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }

    }
}
