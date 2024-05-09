package uz.gita.mobilebanking.presentation.read_card_number

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme

class ReadCardNumberScreen : Screen {
    @Composable
    override fun Content() {
        MobileBankingTheme {
            val viewModel: ReadCardNumberContract.Model = getViewModel<ReadCardNumberModel>()
            ReadCardNumberContent(
                viewModel.collectAsState().value,
                viewModel::onEventDispatcher
            )
        }
    }
}

@Composable
fun ReadCardNumberContent(
    uiState: ReadCardNumberContract.UIState,
    onEventDispatcher: (ReadCardNumberContract.Intent) -> Unit
) {

}

@[Composable Preview]
fun ReadCardNumberPreview() {
    ReadCardNumberContent(ReadCardNumberContract.UIState, {})
}