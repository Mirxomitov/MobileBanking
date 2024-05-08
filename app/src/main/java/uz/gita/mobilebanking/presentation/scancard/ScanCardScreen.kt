package uz.gita.mobilebanking.presentation.scancard

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme

class ScanCardScreen : Screen {
    @Composable
    override fun Content() {
        MobileBankingTheme {
            ScanCardContent()
        }
    }
}

@Composable
private fun ScanCardContent() {

}

@Preview
@Composable
private fun ScanCardPreview() {

}
