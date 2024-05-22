package uz.gita.mobilebanking.presentation.payments

import ItemPay
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.Constants
import uz.gita.mobilebanking.presentation.main.components.PaynetAvia
import uz.gita.mobilebanking.presentation.payments.components.ItemPaySmall
import uz.gita.mobilebanking.presentation.payments.components.TrainTicket
import uz.gita.mobilebanking.presentation.transfers.components.AddTemplate
import uz.gita.mobilebanking.presentation.transfers.components.ItemSelfTransfer
import uz.gita.mobilebanking.presentation.transfers.components.Template
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.theme.AuthComponentBg
import uz.gita.mobilebanking.ui.theme.CardColor
import uz.gita.mobilebanking.ui.theme.PrimaryColor
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.ui.theme.TextColorLight
import uz.gita.mobilebanking.utils.toLog

object PaymentsScreen : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.payment)
            val icon = painterResource(id = R.drawable.wallet)

            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        PaymentContent()
    }
}

@Composable
fun PaymentContent() {
    var searchText by remember { mutableStateOf("") }
    var visible by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var isSearchBarFocused by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(CardColor)
            .imePadding(),
        topBar = {
                Row(
                    modifier = Modifier.wrapContentHeight()
                ) {
                TextBold(
                    modifier = Modifier.padding(12.dp),
                    text = stringResource(id = R.string.payment),
                    fontSize = 32.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.weight(1f))

                AnimatedVisibility(
                    visible = visible,
                    enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
                ) {
                    Button(
                        onClick = {
                            visible = false
                            searchText = ""
                            focusManager.clearFocus(true)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        TextBold(text = "Bekor qilish", fontSize = 18.sp)
                    }
                }
            }
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .height(56.dp)
                    .shadow(
                        1.dp,
                        shape = RoundedCornerShape(16.dp),
                        ambientColor = ShadowColorCard
                    )
                    .background(AuthComponentBg)
                    .clickable(interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            toLog("request focus")
                            focusRequester.requestFocus()
                        }
                    )
                    .padding(12.dp)
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = TextColorLight
                )

                Box(modifier = Modifier) {
                    BasicTextField(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .focusRequester(focusRequester)
                            .onFocusChanged {
                                if (it.isFocused) visible = true
                                isSearchBarFocused = it.isFocused
                            },
                        value = searchText,
                        onValueChange = {
                            searchText = it
                        },
                        maxLines = 1,
                        cursorBrush = Brush.linearGradient(listOf(PrimaryColor, PrimaryColor)),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 18.sp
                        ),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            imeAction = ImeAction.Done,
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            }
                        ),
                    )
                }

                Spacer(Modifier.weight(1f))

                if (searchText.trim() != "" && isSearchBarFocused) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_clear), contentDescription = "clear search",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {
                                    searchText = ""
                                }
                            ),
                        tint = TextColorLight
                    )
                }
            }

            when (visible) {
                true -> Searching()
                false -> Default()
            }

            Spacer(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .height(Constants.BOTTOM_NAVIGATION_HEIGHT.dp)
            )
        }
    }
}

@Composable
private fun Searching() {
    TextBoldBlack(
        text = "Ommaboplari",
        fontSize = 18.sp,
        color = Color.Black,
        modifier = Modifier.padding(top = 12.dp)
    )

    for (i in 0..12) {
        ItemPaySmall(
            Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
            R.drawable.ic_search,
            "$i Elektroenergiya",
            "$i \"HUDUDIY ELEKTR TARMOQLARI\" AJ"
        )
    }
}

@Composable
private fun Default() {
    TextBoldBlack(
        text = stringResource(R.string.templates),
        fontSize = 18.sp,
        modifier = Modifier.padding(top = 24.dp, start = 12.dp)
    )

    LazyRow(
        modifier = Modifier.padding(top = 12.dp),
        contentPadding = PaddingValues(start = 12.dp),
    ) {

        item {
            AddTemplate(Modifier.padding(end = 24.dp), onClick = {})
        }
        items(10) {
            Template(
                modifier = Modifier.padding(end = 24.dp),
                firstName = "Saidrasul",
                imageID = R.drawable.logo_tbc
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 12.dp, end = 12.dp)
    ) {
        ItemSelfTransfer(
            modifier = Modifier
                .padding(end = 12.dp)
                .weight(1f),
            title = stringResource(R.string.qr_code),
            iconID = R.drawable.self_transfer_to_card,
            bgColor = Color(0xFFECF0F2)
        )

        ItemSelfTransfer(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.fast_payments),
            iconID = R.drawable.self_transfer_to_wallet,
            bgColor = Color(0xFFF2EDFD)
        )
    }

    PaynetAvia(
        Modifier
            .padding(top = 24.dp)
            .padding(horizontal = 12.dp)
    )

    TrainTicket(
        Modifier
            .padding(top = 24.dp)
            .padding(horizontal = 12.dp)
    )

    TextBoldBlack(
        text = stringResource(R.string.payment_types),
        fontSize = 18.sp,
        modifier = Modifier.padding(vertical = 24.dp, horizontal = 12.dp)
    )


    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp, end = 6.dp)
        ) {
            // item aloqa
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .height(160.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFF8EAB0))
            ) {
                TextBoldBlack(
                    modifier = Modifier.padding(start = 12.dp, top = 12.dp),
                    text = "Aloqa",
                    fontSize = 18.sp,
                )

                Image(
                    modifier = Modifier
                        .height(128.dp),
                    painter = painterResource(id = R.drawable.communication),
                    contentDescription = null,
                )
            }

            // item Internet va TV
            Column(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .height(124.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFD0F3FC))
            ) {
                TextBoldBlack(
                    modifier = Modifier.padding(start = 12.dp, top = 12.dp),
                    text = "Internet va TV",
                    fontSize = 18.sp,
                )

                Image(
                    modifier = Modifier
                        .height(72.dp),
                    painter = painterResource(id = R.drawable.internet),
                    contentDescription = null,
                )
            }

            // item Ta'lim
            Column(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .height(124.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFFCE4DE))
            ) {
                TextBoldBlack(
                    modifier = Modifier.padding(start = 12.dp, top = 12.dp),
                    text = "Ta'lim",
                    fontSize = 18.sp,
                )

                Image(
                    modifier = Modifier
                        .height(72.dp),
                    painter = painterResource(id = R.drawable.education),
                    contentDescription = null,
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 6.dp, end = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFECF0F2))
                    .padding(12.dp)
            ) {
                TextBold(
                    text = stringResource(R.string.props_by_pays),
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }

            // kommunal to'lovlar
            Column(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .height(164.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFD6F8E8))
            ) {
                TextBoldBlack(
                    modifier = Modifier.padding(start = 12.dp, top = 12.dp),
                    text = "Kommunal to'lovlar",
                    fontSize = 18.sp,
                )

                Image(
                    modifier = Modifier
                        .height(132.dp)
                        .align(Alignment.End),
                    painter = painterResource(id = R.drawable.communal),
                    contentDescription = null,
                )
            }

            // Davlat xizmatlari va shtatlar
            Column(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .height(164.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFECE6FD))
            ) {
                TextBoldBlack(
                    modifier = Modifier.padding(start = 12.dp, top = 12.dp),
                    text = "Davlat xizmatlari va shtatlar",
                    fontSize = 18.sp,
                )

                Image(
                    modifier = Modifier
                        .height(132.dp)
                        .align(Alignment.End),
                    painter = painterResource(id = R.drawable.government),
                    contentDescription = null,
                )
            }
        }
    }

    Row(
        modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp, end = 6.dp)
        ) {
            ItemPay(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                title = "Muddatli to'lovlar", iconID = R.drawable.ic_add_black,
                onClick = {}
            )

            ItemPay(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                title = "Kredit to'lovlar", iconID = R.drawable.ic_add_black,
                onClick = {}
            )

            ItemPay(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                title = "Mikrokredit, lombardlar", iconID = R.drawable.ic_add_black,
                onClick = {}
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 12.dp, start = 6.dp)
        ) {
            ItemPay(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                title = "Transport va taksi", iconID = R.drawable.ic_add_black,
                onClick = {}
            )

            ItemPay(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                title = "Tibbiyot", iconID = R.drawable.ic_add_black,
                onClick = {}
            )

            ItemPay(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                title = "Boshqa to'lovlar", iconID = R.drawable.ic_add_black,
                onClick = {}
            )
        }

    }
}

@Preview
@Composable
fun PaymentContentPreview() {
    PaymentContent()
}
