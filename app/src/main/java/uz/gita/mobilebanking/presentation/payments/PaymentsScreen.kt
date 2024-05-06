package uz.gita.mobilebanking.presentation.payments

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.presentation.main.components.PaynetAvia
import uz.gita.mobilebanking.presentation.payments.components.TrainTicket
import uz.gita.mobilebanking.presentation.transactions.components.AddTemplate
import uz.gita.mobilebanking.presentation.transactions.components.ItemSelfTransfer
import uz.gita.mobilebanking.presentation.transactions.components.Template
import uz.gita.mobilebanking.ui.components.custom_text.TextBold
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.ShadowColorCard
import uz.gita.mobilebanking.ui.theme.authComponentBg
import uz.gita.mobilebanking.ui.theme.cardColor
import uz.gita.mobilebanking.ui.theme.primaryColor
import uz.gita.mobilebanking.ui.theme.textColor
import uz.gita.mobilebanking.ui.theme.textColorLight
import uz.gita.mobilebanking.utils.logger

class PaymentsScreen : Screen {
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
            .background(cardColor),
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
                    .background(authComponentBg)
                    .clickable(interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            logger("request focus")
                            focusRequester.requestFocus()
                        }
                    )
                    .padding(12.dp)
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = textColorLight
                )

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
                    cursorBrush = Brush.linearGradient(listOf(primaryColor, primaryColor)),
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
                ) {
                    if (searchText == "") {
                        TextNormal(
                            text = stringResource(id = R.string.search),
                            fontSize = 18.sp,
                            color = textColorLight
                        )
                    }
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
                        tint = textColorLight
                    )
                }
            }

            when (visible) {
                true -> {
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

                false -> {
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
                        modifier = Modifier.padding(top = 24.dp, start = 12.dp)
                    )

//                    LazyVerticalStaggeredGrid(
//                        columns = StaggeredGridCells.Fixed(2),
//                        verticalItemSpacing = 12.dp,
//                        horizontalArrangement = Arrangement.spacedBy(12.dp),
//                        content = {
//
//                            item {
//                                ItemPay(title = "Aloqa1", resource = R.drawable.paynet_avia_image)
//                            }
//
//                            item {
//                                ItemPay(title = "Aloqa2", resource = R.drawable.paynet_avia_image)
//                            }
//
//                            item {
//                                ItemPay(title = "Aloqa3", resource = R.drawable.paynet_avia_image)
//                            }
//
//                            item {
//                                ItemPay(title = "Aloqa4", resource = R.drawable.paynet_avia_image)
//                            }
//                        },
//                        modifier = Modifier.fillMaxWidth()
//                    )

                }
            }
        }
    }
}

@Composable
private fun ItemPay(
    modifier: Modifier = Modifier, title: String, resource: Int
) {
    Column(
        modifier = modifier.padding(12.dp)
    ) {
        TextBoldBlack(
            text = title, fontSize = 18.sp, modifier = Modifier.padding(top = 24.dp, start = 12.dp)
        )
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = resource),
            contentDescription = null,
        )
    }
}

@Composable
private fun ItemPaySmall(
    modifier: Modifier = Modifier,
    @DrawableRes imageID: Int,
    mainTitle: String,
    secondTitle: String
) {
    Row(
        modifier = modifier
            .shadow(
                elevation = 1.dp, ambientColor = ShadowColorCard, shape = RoundedCornerShape(16.dp)
            )
            .background(cardColor)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(36.dp)
                .padding(4.dp),
            painter = painterResource(id = imageID),
            contentDescription = null
        )

        Column(
            modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center
        ) {
            TextBoldBlack(
                text = mainTitle, fontSize = 18.sp, color = Color.Black
            )
            TextNormal(
                modifier = Modifier.padding(top = 4.dp),
                text = secondTitle,
                fontSize = 14.sp,
                color = textColorLight
            )
        }

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF63D18C))
                .padding(4.dp)
        ) {
            TextNormal(color = textColor, fontSize = 14.sp, text = "2.2")
            TextNormal(color = textColor, fontSize = 14.sp, text = "%")
        }
    }
}

@Preview
@Composable
fun PaymentContentPreview() {
    PaymentContent()
}

/*

Focus true bo'lsa buttonni yoqaman
button bosilgandan buttonni va focusni o'chiraman

 */