package uz.gita.mobilebanking.utils.navigation

import cafe.adriel.voyager.core.screen.Screen

typealias MyScreen = Screen

interface AppNavigator {
    suspend fun addScreen(screen: MyScreen)
    suspend fun replaceScreen(screen: MyScreen)
    suspend fun back()
    suspend fun backUntil(screen: MyScreen)
    suspend fun backToRoot()
}