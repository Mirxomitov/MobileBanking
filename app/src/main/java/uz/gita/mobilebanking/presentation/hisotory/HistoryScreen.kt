package uz.gita.mobilebanking.presentation.hisotory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.utils.previewStateOf

class HistoryScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: HistoryContract.Model = getViewModel<HistoryModel>()

        HistoryContent(
            uiState = viewModel.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher
        )
    }
}

@Composable
fun HistoryContent(
    uiState: State<HistoryContract.UIState>,
    onEventDispatcher: (HistoryContract.Intent) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { onEventDispatcher(HistoryContract.Intent.GetHistory) }) {
            Text(text = "GET HISTORY")
        }
    }
}


@Composable
@Preview
fun HistoryPreview() {
    MobileBankingTheme {
        HistoryContent(previewStateOf(value = HistoryContract.UIState), {})
    }
}