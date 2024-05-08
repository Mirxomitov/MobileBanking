package uz.gita.mobilebanking.presentation.auth

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.ui.dialogs.ChangeLanguage
import uz.gita.mobilebanking.ui.components.Flag
import uz.gita.mobilebanking.ui.components.PhoneInput
import uz.gita.mobilebanking.ui.components.buttons.NextButton
import uz.gita.mobilebanking.ui.components.custom_text.TextBoldBlack
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.ui.theme.primaryColor
import uz.gita.mobilebanking.ui.theme.textColor
import uz.gita.mobilebanking.ui.theme.textColorLight

class AuthScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: AuthContract.Model = getViewModel<AuthModel>()
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

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
            containerColor = primaryColor,
            onClick = {
                onEventDispatcher(AuthContract.Intent.SignIn(phone))
            },
        )

        TextNormal(
            text = stringResource(id = R.string.oferta),
            textAlign = TextAlign.Center,
            color = textColorLight,
            letterSpacing = 0.5.sp,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview(

) {
    AuthScreenContent(
        {},
        AuthContract.UIState("", R.drawable.ic_flag_uz)
    )
}