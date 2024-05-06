package uz.gita.mobilebanking.presentation.payments.components

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.textColor

@Composable
fun TrainTicket(modifier: Modifier = Modifier) {
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
                        .padding(8.dp)
                ) {
                    TextBold(text = stringResource(id = R.string.train_ticket), fontSize = 18.sp)

                    TextNormal(text = stringResource(R.string.convenient_and_fast), color = textColor, fontSize = 14.sp)
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
                TextNormal(text = stringResource(R.string.buy), color = Black, fontSize = 14.sp)
            }
        }
    }
}
