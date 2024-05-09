package uz.gita.mobilebanking.presentation.read_card_number

import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject

interface ReadCardNumberDirection {
    suspend fun back()
}

class ReadCardNumberDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : ReadCardNumberDirection {
    override suspend fun back() {
        appNavigator.back()
    }
}