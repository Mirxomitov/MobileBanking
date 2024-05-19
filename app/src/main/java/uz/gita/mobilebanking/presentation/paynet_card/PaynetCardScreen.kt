package uz.gita.mobilebanking.presentation.paynet_card

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.GrayIcon
import uz.gita.mobilebanking.ui.theme.MainBgLight
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.ui.theme.ShadowColorCard

class PaynetCardScreen : Screen {
    @Composable
    override fun Content() {
        MobileBankingTheme {
            val viewModel: PaynetCardContract.Model = getViewModel<PaynetCardModel>()
            PaynetCardContent(
                viewModel.collectAsState().value,
                viewModel::onEventDispatcher
            )
        }
    }
}

@Composable
fun PaynetCardContent(
    uiState: PaynetCardContract.UIState,
    onEventDispatcher: (PaynetCardContract.Intent) -> Unit
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(MainBgLight),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "back",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                onEventDispatcher(PaynetCardContract.Intent.Back)
                            }
                        ),
                    tint = GrayIcon,
                )
            }
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBgLight)
                .padding(it)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Image(
                    painter = painterResource(id = R.drawable.paynet_card_info),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                )

                TextBoldBlack(
                    modifier = Modifier.padding(top = 12.dp),
                    text = stringResource(R.string.what_is_paynet_card),
                    fontSize = 28.sp,
                )

                TextNormal(
                    modifier = Modifier.padding(top = 18.dp, bottom = 4.dp),
                    text = stringResource(R.string.transfer_without_comission),
                    fontSize = 18.sp,
                    color = Black
                )

                Item(
                    modifier = Modifier.padding(top = 8.dp),
                    iconId = R.drawable.ic_back,
                    title = R.string.to_fill,
                    description = R.string.in_agent_or_infokiosk_or_bank_card
                )

                Item(
                    modifier = Modifier.padding(top = 8.dp),
                    iconId = R.drawable.ic_back,
                    title = R.string.zero_percent_for_transfers,
                    description = R.string.to_other_paynet_cards
                )

                Item(
                    modifier = Modifier.padding(top = 8.dp),
                    iconId = R.drawable.ic_back,
                    title = R.string.for_cash,
                    description = R.string.on_paynet_agent
                )

                Item(
                    modifier = Modifier.padding(top = 8.dp),
                    iconId = R.drawable.ic_back,
                    title = R.string.payments_by_card,
                    description = R.string.can_pay_for_any_thousand_services
                )

                Item(
                    modifier = Modifier.padding(top = 8.dp),
                    iconId = R.drawable.ic_back,
                    title = R.string.until_2_2_percent_cash_back,
                    description = R.string.fill_cash_back_wallet
                )

                Spacer(modifier = Modifier.height(72.dp))
            }

            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 1.dp,
                        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
                        ambientColor = ShadowColorCard
                    )
                    .background(White)
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.Center,

                ) {
                Button(
                    onClick = {
                        // TODO Show bottom sheet dialog
                    },
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50),
                        contentColor = White,
                        disabledContainerColor = Color(0xFFB1E0B2),
                        disabledContentColor = White,
                    )
                ) {
                    TextNormal(
                        modifier = Modifier.padding(6.dp),
                        text = stringResource(R.string.know_the_restrictions_and_conditions),
                        fontSize = 14.sp,
                        color = White
                    )
                }
            }
        }
    }
}

@Composable
fun Item(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    @StringRes title: Int,
    @StringRes description: Int,
) {
    Row(
        modifier
            .fillMaxWidth()
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = ShadowColorCard,
            )
            .background(White)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Icon(
            modifier = Modifier
                .padding(4.dp)
                .size(24.dp),
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = GrayIcon
        )

        Column(modifier = Modifier.weight(1f)) {
            TextNormal(
                text = stringResource(id = title),
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 1.dp),
                color = Black
            )

            TextNormal(
                text = stringResource(id = description),
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 1.dp),
                color = GrayIcon
            )
        }


    }
}

@Preview
@Composable
fun PaynetCardPreview() {
    PaynetCardContent(
        uiState = PaynetCardContract.UIState,
        onEventDispatcher = {}
    )
}