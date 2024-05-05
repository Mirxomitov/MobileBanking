package uz.gita.mobilebanking.presentation.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.ui.theme.cardColor
import uz.gita.mobilebanking.ui.theme.grayIcon
import uz.gita.mobilebanking.ui.theme.textColor
import uz.gita.mobilebanking.ui.theme.textColorLight
import java.io.Serializable


data class PersonalInformation(val onClickHide: () -> Unit) : Screen, Serializable {
    @Composable
    override fun Content() {
        MobileBankingTheme {
            PersonalInformationContent(onClickHide)
        }
    }
}

@Composable
private fun PersonalInformationContent(
    onClickHide : () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(cardColor)
            .padding(12.dp)
    ) {
        Box(
            modifier = Modifier
                .height(4.dp)
                .width(48.dp)
                .clip(CircleShape)
                .background(grayIcon)
                .align(Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextBold(
                color = Color.Black, text = stringResource(id = R.string.personal_informations),
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier
                    .size(18.dp)
                    .clickable { onClickHide() },
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "close"
            )
        }

        TextNormal(
            modifier = Modifier.padding(top = 12.dp),
            color = textColor, text = stringResource(R.string.call_us_if_your_identification_data_changed),
            fontSize = 16.sp,
        )

        Item(Modifier.padding(top = 12.dp), stringResource(id = R.string.family_name), "Mirxomitov")
        Item(Modifier.padding(top = 12.dp), stringResource(R.string.name), "Tohir")
        Item(Modifier.padding(top = 12.dp), stringResource(R.string.patronymic), "Mirolim o'g'li")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Item(Modifier, stringResource(R.string.document_number), "AB1234567")
            Spacer(modifier = Modifier.weight(1f))
            Item(Modifier, stringResource(R.string.date_of_birth), "29.04.2004")
            Spacer(modifier = Modifier.weight(1f))
        }
        Item(Modifier.padding(top = 12.dp), stringResource(R.string.phone_number), "+998 90 355 36 20")
    }
}

@Composable
private fun Item(
    modifier: Modifier = Modifier,
    description: String,
    value: String
) {
    Column(modifier) {
        TextNormal(color = textColorLight, text = description)
        TextNormal(color = textColor, text = value.uppercase())
    }
}

@Preview
@Composable
fun PersonalInformationPreview() {
    PersonalInformationContent({})
}