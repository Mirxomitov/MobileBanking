package uz.gita.mobilebanking.presentation.hisotory

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme

class HistoryScreen : Screen {
    @Composable
    override fun Content() {

    }
}

@Composable
fun HistoryContent() {

}

@Composable
@Preview
fun HistoryPreview() {
    MobileBankingTheme {
        HistoryContent()
    }
}