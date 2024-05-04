package uz.gita.mobilebanking.presentation.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.grayIcon

@Composable
fun UserBalanceWithEye(balance: String, modifier: Modifier) {
    Column(modifier = modifier) {
        TextNormal(
            text = stringResource(id = R.string.my_money),
            fontSize = 12.sp,
            color = Color(0xFF9EA2A7)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextBoldBlack(text = balance, fontSize = 22.sp, modifier = Modifier.padding(end = 2.dp))

            TextBold(text = stringResource(id = R.string.som), fontSize = 22.sp)
            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.ic_eye),
                contentDescription = "eye",
                modifier = Modifier.size(32.dp),
                tint = grayIcon
            )
        }
    }
}