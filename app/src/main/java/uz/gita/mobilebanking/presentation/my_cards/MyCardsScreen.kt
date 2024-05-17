package uz.gita.mobilebanking.presentation.my_cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.data.model.ui.CardData
import uz.gita.mobilebanking.presentation.my_cards.components.ItemCard
import uz.gita.mobilebanking.presentation.my_cards.components.TopBar
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.utils.previewStateOf


data class MyCardsScreen(val listOfCards: List<CardData>) : Screen {
    @Composable
    override fun Content() {
        val viewModel : MyCardsContract.Model = getViewModel<MyCardsModel>()

        MyCardsContent(
            uiState = viewModel.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher
        )
    }
}

@Composable
fun MyCardsContent(
    uiState: State<MyCardsContract.UIState>,
    onEventDispatcher: (MyCardsContract.Intent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(onClickBack = {})
        }
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {

        }
    }
}

@Preview
@Composable
fun ContentPreview() {
    MobileBankingTheme {
        MyCardsContent(previewStateOf(MyCardsContract.UIState), {})
    }
}
@Preview
@Composable
fun ItemCardPreview() {
    ItemCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        cardData = CardData(
            "0005", "2028", "6", "Personal", "100000000"
        )
    )
}
