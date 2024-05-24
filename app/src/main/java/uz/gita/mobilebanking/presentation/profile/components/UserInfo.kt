package uz.gita.mobilebanking.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.model.FullInfoData
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.ui.theme.TextColor
import uz.gita.mobilebanking.utils.toPhoneNumber

@Composable
fun UserInfo(
    modifier: Modifier = Modifier,
    userData: FullInfoData
) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 4.dp,
                RoundedCornerShape(16.dp),
                ambientColor = ShadowColorCard
            )
            .background(White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            TextBold(
                letterSpacing = 0.8.sp,
                text = "${userData.firstName} ${userData.lastName}",
                color = Black,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            TextBold(
                letterSpacing = 0.8.sp,
                text = userData.phone.toPhoneNumber(),
                color = TextColor,
                modifier = Modifier.padding(top = 12.dp, start = 8.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .shadow(
                        elevation = 3.dp,
                        RoundedCornerShape(16.dp),
                        ambientColor = ShadowColorCard
                    )
                    .background(White),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(56.dp),
                        painter = painterResource(id = R.drawable.paynet),
                        contentDescription = "state icon"
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        TextNormal(
                            letterSpacing = 0.8.sp,
                            text = "Sizning holatingiz",
                            fontSize = 14.sp,
                            color = TextColor
                        )

                        TextBold(
                            letterSpacing = 0.8.sp,
                            text = "Tasdiqlangan",
                            fontSize = 16.sp,
                            color = Black
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    UserInfo(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        userData = FullInfoData(
            0L,
            "Tohir",
            0,
            "Mirxomitov",
            "+998903553620"
        )
    )
}

