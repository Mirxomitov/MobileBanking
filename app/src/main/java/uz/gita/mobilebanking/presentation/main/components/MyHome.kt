package uz.gita.mobilebanking.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.ui.theme.PrimaryColor
import uz.gita.mobilebanking.ui.theme.textColor

@Composable
fun MyHome(
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier
            .height(132.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = ShadowColorCard
            )
            .background(Color(0xFFD5F2E4))
    ) {

        Column(
            Modifier
                .weight(1f)
                .height(132.dp)
                .padding(start = 12.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TextBoldBlack(
                modifier = Modifier.padding(start = 2.dp),
                text = stringResource(R.string.my_home),
                fontSize = 18.sp
            )

            TextNormal(
                text = stringResource(R.string.pay_in_time_to_elektr_gas_and_call),
                color = textColor,
                fontSize = 14.sp
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(PrimaryColor)
                    .clickable { },
            ) {
                TextBold(
                    text = "Uyni yaratish",
                    color = White,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.my_home_example_uz),
            contentDescription = null,
            modifier = Modifier.height(132.dp)
        )
    }
}

@Preview
@Composable
fun MyHomePreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        MyHome(Modifier.fillMaxWidth())
    }
}