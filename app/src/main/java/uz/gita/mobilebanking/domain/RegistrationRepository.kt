package uz.gita.mobilebanking.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.response.SignInResponse
import uz.gita.mobilebanking.data.model.response.SignUpResponse
import uz.gita.mobilebanking.data.model.response.SignInVerifyResponse
import uz.gita.mobilebanking.data.model.response.SignUpVerifyResponse

interface RegistrationRepository {
    fun signIn(phoneNumber: String): Flow<Result<SignInResponse>>
    fun signUp(phoneNumber: String): Flow<Result<SignUpResponse>>
    fun signInVerify(token: String, verificationCode: String): Flow<Result<SignInVerifyResponse>>
    fun signUpVerify(token: String, verificationCode: String): Flow<Result<SignUpVerifyResponse>>

    fun phoneNumber(phoneNumber: String)
    fun phoneNumber() : String
    fun isLanguageUzbek(): Boolean
    fun saveActiveLanguage(isUzbek : Boolean)
}