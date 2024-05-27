package uz.gita.mobilebanking.presentation.main

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.presentation.main.components.MainTop
import uz.gita.mobilebanking.presentation.main.components.MainTopShimmer
import uz.gita.mobilebanking.presentation.main.states.OnContent
import uz.gita.mobilebanking.presentation.main.states.OnLoading
import uz.gita.mobilebanking.utils.previewStateOf

object MainScreen : Tab {
    override val options: TabOptions
        @Composable get() {
            val title = stringResource(R.string.main)
            val icon = painterResource(id = R.drawable.home)

            return remember {
                TabOptions(
                    index = 0u, title = title, icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel: MainContract.Model = getViewModel<MainModel>()

        MainContent(
            uiState = viewModel.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher
        )
    }
}

@Composable
private fun MainContent(
    uiState: State<MainContract.UIState>,
    onEventDispatcher: (MainContract.Intent) -> Unit
) {
    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            when (uiState.value) {
                is MainContract.UIState.Content -> {
                    MainTop(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(horizontal = 12.dp),
                        userName = (uiState.value as MainContract.UIState.Content).firstName,
                        onClickItem = {
                            onEventDispatcher(MainContract.Intent.OpenProfileScreen)
                        },
                        icSupportClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://chat.paynet.uz/"))
                            startActivity(context, intent, null)
                        },
                        icNotificationClick = {
                            //
                        },
                    )
                }

                else -> {
                    MainTopShimmer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(horizontal = 12.dp),
                        onClickItem = {
                            onEventDispatcher(MainContract.Intent.OpenProfileScreen)
                        },
                        icSupportClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://chat.paynet.uz/"))
                            startActivity(context, intent, null)
                        },
                        icNotificationClick = {

                        },
                    )
                }
            }
        }
    ) { paddingValues ->
        when (uiState.value) {
            is MainContract.UIState.Content -> OnContent(paddingValues, uiState, onEventDispatcher)
            else -> OnLoading(paddingValues, uiState, onEventDispatcher)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainContent(previewStateOf(value = MainContract.UIState.Loading), {})
}



