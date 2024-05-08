package uz.gita.mobilebanking.presentation.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import uz.gita.mobilebanking.ui.components.custom_text.TextNormal
import uz.gita.mobilebanking.ui.theme.mainBgLight
import uz.gita.mobilebanking.ui.theme.primaryColor
import uz.gita.mobilebanking.ui.theme.textColor

class BottomNavigation : Screen {
    @Composable
    override fun Content() {
        TabNavigator(MainTab) {
            Scaffold(
                content = {
                    Box(modifier = Modifier.padding(it))
                    CurrentTab()
                },
                bottomBar = {
                    Row(
                        modifier = Modifier.background(mainBgLight)
                    ) {
                        TabNavigationItem(MainTab)
                        TabNavigationItem(TransfersTab)
                        TabNavigationItem(PaymentTab)
                        TabNavigationItem(HistoryTab)
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
                tint = if (tabNavigator.current == tab) primaryColor else Color.Black,
                painter = tab.options.icon!!,
                contentDescription = tab.options.title
            )
        },
        label = {
            TextNormal(
                text = tab.options.title,
                fontSize = 10.sp,
                color = textColor
            )
        },
    )
}