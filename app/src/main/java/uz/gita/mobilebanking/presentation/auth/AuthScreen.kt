package uz.gita.mobilebanking.presentation.auth

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.components.Flag
import uz.gita.mobilebanking.ui.components.PhoneInput
import uz.gita.mobilebanking.ui.components.buttons.NextButton
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.dialogs.ChangeLanguage
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.ui.theme.PrimaryColor
import uz.gita.mobilebanking.ui.theme.textColor

class AuthScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: AuthContract.Model = getViewModel<AuthModel>()
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        val context = LocalContext.current

        viewModel.onEventDispatcher(AuthContract.Intent.GetLanguage)

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is AuthContract.SideEffect.LanguageDialog -> {
                    bottomSheetNavigator.show(
                        ChangeLanguage(
                            sideEffect.isCurrentUzbek,
                            onClick = {
                                viewModel.onEventDispatcher(AuthContract.Intent.ChangeLanguage(it))
                                bottomSheetNavigator.hide()
                            },
                            dismiss = {
                                bottomSheetNavigator.hide()
                            }
                        )
                    )
                }

                is AuthContract.SideEffect.OpenOffer -> {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://assets-global.website-files.com/63a7038e6eb0c1f38cd4d11f/659e7e324fed06afd1dbacfb_%D0%BE%D1%84%D0%B5%D1%80%D1%82%D0%B0%20%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D0%BA%D0%B0%202024.pdf")
                    )
                    context.startActivity(intent)
                }
            }
        }

        MobileBankingTheme {
            AuthScreenContent(
                viewModel::onEventDispatcher,
                viewModel.collectAsState().value
            )
        }
    }
}

@Composable
private fun AuthScreenContent(
    onEventDispatcher: (AuthContract.Intent) -> Unit,
    uiState: AuthContract.UIState
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var phone by remember { mutableStateOf("") }

        Flag(
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 16.dp),
            flagIcon = R.drawable.ic_flag_uz,
            onClick = {
                onEventDispatcher(AuthContract.Intent.ChangeLanguageDialog)
            }
        )

        Image(
            painter = painterResource(id = R.drawable.paynet),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 24.dp)
                .size(60.dp)
        )

        TextBoldBlack(
            text = stringResource(id = R.string.enter_phone_number),
            fontSize = 20.sp,
            letterSpacing = 0.8.sp,
            modifier = Modifier.padding(top = 16.dp),
        )

        TextNormal(
            text = stringResource(
                id = R.string.to_be_customer_or_enter
            ),
            color = textColor,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp),
        )

        PhoneInput(
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            getInput = {
                phone = it
            },
            clear = {
                phone = it
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        NextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            isEnabled = phone.length == 9,
            containerColor = PrimaryColor,
            onClick = {
                onEventDispatcher(AuthContract.Intent.SignIn(phone))
            },
        )

        val annotatedString = buildAnnotatedString {
            append(stringResource(id = R.string.oferta1))
            pushStringAnnotation(
                tag = "TTT",
                annotation = "https://assets-global.website-files.com/63a7038e6eb0c1f38cd4d11f/659e7e324fed06afd1dbacfb_%D0%BE%D1%84%D0%B5%D1%80%D1%82%D0%B0%20%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D0%BA%D0%B0%202024.pdf"
            )
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append(stringResource(id = R.string.oferta2))
            }
            append(stringResource(id = R.string.oferta3))
        }

        /*ClickableText(
            modifier = Modifier.fillMaxWidth(),
            text = annotatedString,
            onClick = {
                annotatedString.getStringAnnotations(
                    start = it,
                    end = it,
                ).firstOrNull()?.let {
                    if (it.tag == "TTT")
                        onEventDispatcher.invoke(AuthContract.Intent.OnClickOffer)
                }
            },
        )*/
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreenContent(
        {},
        AuthContract.UIState("", R.drawable.ic_flag_uz)
    )
}