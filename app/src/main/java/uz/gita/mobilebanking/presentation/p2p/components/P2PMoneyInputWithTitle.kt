package uz.gita.mobilebanking.presentation.p2p.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
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
import uz.gita.mobilebanking.ui.theme.textColor
import uz.gita.mobilebanking.ui.theme.TextColorLight

@Composable
fun P2PMoneyInputWithTitle(
    modifier: Modifier = Modifier,
    onError: (Boolean) -> Unit
) {
    val focusRequester = remember { FocusRequester() } // isFocused
    var value by remember {
        mutableStateOf("0 so'm")
    }

    Row(
        modifier
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFFE9EAED))
            .padding(vertical = 4.dp, horizontal = 18.dp)
            .focusRequester(focusRequester)
            .focusable(true),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.weight(1f)) {
            TextNormal(text = stringResource(id = R.string.you_are_transfering), color = TextColorLight)

            BasicTextField(
                value = value.trim(),
                onValueChange = {
                    value = it.trim()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {

                    },
                keyboardActions = KeyboardActions(onDone = { focusRequester.freeFocus() }),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(color = textColor, fontSize = 24.sp),
                singleLine = true,
            )
        }

        if (value != "") {
            Icon(
                painter = painterResource(id = R.drawable.search_clear),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(24.dp)
                    .clickable {
                        value = ""
                    },
                tint = TextColorLight
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    P2PMoneyInputWithTitle(
        onError = {},
        modifier = Modifier
            .fillMaxWidth()
    )
}

private fun String.formatter(): String {
    val s = StringBuilder(this.reversed())
    s.forEachIndexed { index, _ ->
        if (index % 4 == 0) s.insert(index, " ")
    }
    return s.reversed().toString()
}