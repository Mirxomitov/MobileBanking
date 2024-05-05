package uz.gita.mobilebanking.presentation.auth

import uz.gita.mobilebanking.presentation.verify.VerifyScreen
import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject

interface AuthDirection {
    suspend fun toVerifyScreen(token: String, isSignIn: Boolean)
}

class AuthDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : AuthDirection {
    override suspend fun toVerifyScreen(token: String, isSignIn: Boolean) {
        appNavigator.addScreen(VerifyScreen(token, isSignIn))
    }
}