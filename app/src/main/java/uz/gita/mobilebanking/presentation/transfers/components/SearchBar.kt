package uz.gita.mobilebanking.presentation.transfers.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.AuthComponentBg
import uz.gita.mobilebanking.ui.theme.GrayColor
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.utils.logger
import uz.gita.mobilebanking.utils.visual_transformations.CardNumberTransformation

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onClickContacts: () -> Unit,
    onClickScan: () -> Unit,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    onFocusChanged: (FocusState) -> Unit
) {

    var text by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(AuthComponentBg)
            .padding(top = 2.dp, bottom = 2.dp, start = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            if (text.isEmpty()) TextNormal(
                text = stringResource(id = R.string.card_or_phone),
                color = GrayColor,
                fontSize = 18.sp
            )

            BasicTextField(
                value = text,
                onValueChange = {
                    if (it.length <= 16) {
                        text = it
                        onValueChange(text)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        onFocusChanged(it)
                    },
                textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
                visualTransformation = CardNumberTransformation,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                if (text.isEmpty()) {
                    IconButton(onClick = { onClickContacts() }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_contacts),
                            contentDescription = null
                        )
                    }

                    IconButton(onClick = { onClickScan() }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_scan),
                            contentDescription = null
                        )
                    }
                } else {
                    IconButton(onClick = { text = "" }) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            painter = painterResource(id = R.drawable.search_clear),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun SearchBarPreview() {
    MobileBankingTheme {
        SearchBar(
            modifier = Modifier,
            onClickContacts = {},
            onClickScan = {},
            onValueChange = {},
            focusRequester = FocusRequester(),
            onFocusChanged = {}
        )
    }
}