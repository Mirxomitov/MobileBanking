package uz.gita.mobilebanking.presentation.p2p

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.presentation.p2p.components.P2PMoneyInputWithTitle
import uz.gita.mobilebanking.ui.components.TopBarWithBack
import uz.gita.mobilebanking.ui.components.cards.CardP2PSendItem
import uz.gita.mobilebanking.ui.components.cards.CardP2PWithCardNumber
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.p2pScreenBg

class P2PScreen : Screen {
    @Composable
    override fun Content() {
        P2PContent()
    }
}

@Composable
private fun P2PContent() {
    var isInputIncorrect by remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxSize()
            .background(color = p2pScreenBg)
            .padding(12.dp)
    ) {
        TopBarWithBack(modifier = Modifier,
            icon = R.drawable.ic_back,
            title = stringResource(R.string.transfer_to_card),
            onClickIcon = {})

        TextBoldBlack(
            text = stringResource(R.string.from),
            modifier = Modifier.padding(top = 18.dp),
            fontSize = 18.sp,
            letterSpacing = 0.8.sp
        )
        CardP2PSendItem(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp), onClickItem = {})

        TextBoldBlack(
            text = stringResource(R.string.to),
            modifier = Modifier.padding(top = 24.dp),
            fontSize = 18.sp,
            letterSpacing = 0.8.sp
        )

        CardP2PWithCardNumber(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
            onClickItem = {}
        )

        P2PMoneyInputWithTitle(modifier = Modifier.padding(top = 24.dp)) { isInputIncorrect = it }

        if (isInputIncorrect) {
            TextNormal(modifier = Modifier.padding(8.dp), text = stringResource(R.string.commission_0))
        } else {
            TextNormal(modifier = Modifier.padding(8.dp), text = stringResource(R.string.insufficient_funds))
        }
    }
}

@Preview
@Composable
fun P2PPreview() {
    P2PContent()
}