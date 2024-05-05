package uz.gita.mobilebanking.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

@Composable
fun MyInformation(
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
            text = stringResource(R.string.my_informations),
            fontSize = 16.sp,
            color = textColor
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.personal_information),
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(24.dp)
            )

            TextNormal(
                text = stringResource(R.string.personal_informations),
                modifier = Modifier.padding(start = 8.dp),
                color = textColor
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.ic_chevronright),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview
@Composable
fun MyInformationPreview() {
    MyInformation()
}