package uz.gita.mobilebanking.presentation.read_card_number

import uz.gita.mobilebanking.presentation.addcard.AddCardScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject

interface ReadCardNumberDirection {
    suspend fun back()
    suspend fun backWithData(cardNumber: String, expirationDate: String)

    suspend fun backWithCardNumber(cardNumber: String)
}

class ReadCardNumberDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : ReadCardNumberDirection {
    override suspend fun back() {
        appNavigator.back()
    }

    override suspend fun backWithData(cardNumber: String, expirationDate: String) {
        appNavigator.back()
        appNavigator.replaceScreen(
            AddCardScreen(cardNumber = cardNumber, expirationDate = expirationDate)
        )
    }

    override suspend fun backWithCardNumber(cardNumber: String) {
        appNavigator.back()
    }
}