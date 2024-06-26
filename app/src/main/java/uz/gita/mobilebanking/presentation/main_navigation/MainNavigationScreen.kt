package uz.gita.mobilebanking.presentation.main_navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import uz.gita.mobilebanking.presentation.history.HistoryScreen
import uz.gita.mobilebanking.presentation.main.MainScreen
import uz.gita.mobilebanking.presentation.payments.PaymentsScreen
import uz.gita.mobilebanking.presentation.transfers.TransfersScreen
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.GrayIcon
import uz.gita.mobilebanking.ui.theme.MainBgLight
import uz.gita.mobilebanking.ui.theme.PrimaryColor
import uz.gita.mobilebanking.utils.Constants.BOTTOM_NAVIGATION_HEIGHT

class MainNavigationScreen : Screen {
    @Composable
    override fun Content() {
        TabNavigator(MainScreen) {
            Scaffold(
                content = {
                    Box(modifier = Modifier.padding(it))
                    CurrentTab()
                },
                bottomBar = {
                    Row(
                        modifier = Modifier
                            .height(BOTTOM_NAVIGATION_HEIGHT.dp)
                            .background(MainBgLight),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TabNavigationItem(MainScreen)
                        TabNavigationItem(TransfersScreen)
                        TabNavigationItem(PaymentsScreen)
                        TabNavigationItem(HistoryScreen)
                    }
                }
            )
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            Icon(
                tint = if (tabNavigator.current == tab) PrimaryColor else GrayIcon,
                painter = tab.options.icon!!,
                contentDescription = tab.options.title
            )
        },
        label = {
            TextNormal(
                text = tab.options.title,
                fontSize = 10.sp,
                color = GrayIcon
            )
        },
    )
}