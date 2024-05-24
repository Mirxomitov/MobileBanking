package uz.gita.mobilebanking.presentation.transfer_success

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.theme.BackgroundWhite90
import uz.gita.mobilebanking.ui.theme.Gray40
import uz.gita.mobilebanking.ui.theme.Gray50
import uz.gita.mobilebanking.ui.theme.activeButtonGreenColor
import uz.gita.mobilebanking.ui.theme.activeButtonTextColor
import uz.gita.mobilebanking.ui.theme.blackIcon
import uz.gita.mobilebanking.ui.theme.greenText
import uz.gita.mobilebanking.ui.theme.textColorLight90
import uz.gita.mobilebanking.ui.theme.textTint
import uz.gita.mobilebanking.utils.logger
import uz.gita.mobilebanking.utils.toValue

class TransferConfirmationScreen(
    private val amount: String,
    private val receiverName: String,
    private val receiverCardPan: String
) : Screen {
    @Composable
    override fun Content() {

        logger("Transfer confirmation screen")
        val screenViewModel: TransferConfirmationContract.Model = getViewModel<TransferConfirmationViewModel>()

        TransferConfirmationScreenContent(
            onEventDispatcher = screenViewModel::onEventDispatcher,
            amount = amount.toValue(),
            receiverName = receiverName,
            receiverCardPan = receiverCardPan
        )
    }

}

@Composable
fun TransferConfirmationScreenContent(
    onEventDispatcher: (TransferConfirmationContract.Intent) -> Unit,
    amount: String,
    receiverName: String,
    receiverCardPan: String
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite90),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = stringResource(id = R.string.transfer_send),
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            color = textColorLight90
        )

        Spacer(Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_success),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )

        Spacer(modifier = Modifier.size(6.dp))

        Text(
            text = amount,
            color = textColorLight90,
            fontSize = 46.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
            ),
        )

        Text(
            text = stringResource(id = R.string.uzs_sum),
            color = Gray50,
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            fontSize = 24.sp,
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
            )
        )


        Spacer(Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp)
                .padding(horizontal = 16.dp)
                .shadow(elevation = 4.dp, RoundedCornerShape(20.dp), spotColor = Color.Gray)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_wallet_paynet),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(32.dp)
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = receiverName,
                    fontSize = 14.sp,
                    color = textTint,
                    fontFamily = FontFamily(Font(R.font.pnfont_medium))
                )

//                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = receiverCardPan,
                    color = textColorLight90,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_medium))
                )
            }

            Spacer(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(40.dp)
                    .width(70.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xffdeffe9))
            ) {
                Text(
                    text = stringResource(id = R.string.done),
                    color = greenText,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_medium))
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(32.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Gray40)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "${stringResource(id = R.string.commission)} 0 ${stringResource(id = R.string.uzs_sum)}",
                color = textColorLight90,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_medium))
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_operations_star_v2),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = blackIcon
                )


                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = stringResource(id = R.string.add_to_template),
                    color = textColorLight90,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_medium)),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.size(32.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_operations_file_v2),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = blackIcon
                )


                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = stringResource(id = R.string.transfer_information),
                    color = textColorLight90,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_medium)),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(activeButtonGreenColor)
                .clickable(
                    indication = null,
                    interactionSource = remember {
                        MutableInteractionSource()
                    }
                ) {
                    onEventDispatcher.invoke(TransferConfirmationContract.Intent.Ready)
                }
        ) {
            Text(
                text = stringResource(id = R.string.ready),
                color = activeButtonTextColor,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold))
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewTransferConfirmationScreen() {
//
//    PaynetTheme {
//        TransferConfirmationScreen(
//            amount = "200 000",
//            receiverName = "MAQSUDOV ABDULBOSIT",
//            receiverCardPan = "• 0025"
//        )
//    }
//}
