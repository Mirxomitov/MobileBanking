package uz.gita.mobilebanking.presentation.add_template.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.AuthComponentBg
import uz.gita.mobilebanking.ui.theme.GrayIcon
import uz.gita.mobilebanking.ui.theme.TextColor
import uz.gita.mobilebanking.utils.logger

@Composable
fun TemplateNameInput(
    modifier: Modifier,
    inputText: String = "",
    getInput: (String) -> Unit,
) {
    var text: String by remember { mutableStateOf(inputText) }
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    val fontSize by animateDpAsState(targetValue = if (!isFocused) 24.dp else 12.dp, label = "Template Title Size")

    logger("isFocused: $isFocused")
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(AuthComponentBg)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { /*focusRequester.requestFocus()*/ }
            ),
        verticalArrangement = Arrangement.Center
    ) {
        TextNormal(
            text = "Shablon nomi",
            fontSize = fontSize.value.sp,
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { /*focusRequester.captureFocus() */ }
                ),
            color = TextColor
        )

        if (isFocused) {
            Box(modifier = Modifier.weight(1f)) {
                BasicTextField(
                    value = text,
                    onValueChange = {
                        text = it
                        getInput(text)
                    },
                    singleLine = true,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .focusRequester(focusRequester)
                        .focusable()
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) isFocused = true
                            logger("focusState.isFocused :${focusState.isFocused}")
                        },
                    textStyle = TextStyle.Default.copy(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                )
                if (text.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_clear),
                        modifier = Modifier
                            .padding(4.dp)
                            .size(16.dp)
                            .clickable {
                                text = ""
                                getInput("")
                            },
                        tint = GrayIcon,
                        contentDescription = null
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun TemplateNameInputPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        TemplateNameInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp)
                .height(52.dp),
            getInput = {}
        )
    }
}

@Composable
fun TestFocus() {
    var text: String by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Gray)
                .focusable()
                .focusRequester(focusRequester)
                .onFocusChanged {
                    logger("focus: ${it.isFocused}")
//                    Toast.makeText(context, "focus: ${it.isFocused}", Toast.LENGTH_SHORT).show()
                }
        )

        Button(onClick = {
            focusRequester.requestFocus()
        }) { Text(text = "Request focus") }
    }


}

@Preview(showBackground = true)
@Composable
fun TestFocusPreview() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TestFocus()
    }
}