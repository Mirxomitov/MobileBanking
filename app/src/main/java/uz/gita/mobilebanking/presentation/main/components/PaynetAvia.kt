package uz.gita.mobilebanking.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.PrimaryColor
import uz.gita.mobilebanking.ui.theme.textColor
import uz.gita.mobilebanking.ui.theme.TextColorLight

@Composable
fun PaynetAvia(modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier
            .height(132.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFBDDAF1))
        ) {
            Row(Modifier.fillMaxSize()) {
                Column(
                    Modifier
                        .weight(1f)
                        .padding(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                                .size(18.dp)
                                .clip(CircleShape)
                                .background(PrimaryColor)
                                .align(Alignment.Bottom)

                        )
                        TextBoldBlack(modifier = Modifier.padding(start = 2.dp), text = "paynet", fontSize = 24.sp)
                        TextBold(
                            modifier = Modifier.padding(start = 5.dp),
                            text = "avia",
                            color = TextColorLight,
                            fontSize = 24.sp
                        )
                    }

                    TextNormal(text = stringResource(R.string.useful_and_reliable), color = textColor, fontSize = 14.sp)
                }

                Image(
                    painter = painterResource(id = R.drawable.paynet_avia_image),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentScale = ContentScale.FillWidth
                )
            }

            Button(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .align(Alignment.BottomStart)
                    .clip(CircleShape),
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = White)
            ) {
                TextNormal(text = stringResource(R.string.buy_ticket), color = Black, fontSize = 14.sp)
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    PaynetAvia()
}