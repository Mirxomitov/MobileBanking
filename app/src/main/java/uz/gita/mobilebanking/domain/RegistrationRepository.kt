package uz.gita.mobilebanking.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.response.auth.LogOutResponse
import uz.gita.mobilebanking.data.model.response.auth.SignInResponse
import uz.gita.mobilebanking.data.model.response.auth.SignUpResponse
import uz.gita.mobilebanking.data.model.response.auth.SignInVerifyResponse
import uz.gita.mobilebanking.data.model.response.auth.SignUpVerifyResponse

interface RegistrationRepository {
    fun signIn(phoneNumber: String): Flow<Result<SignInResponse>>
    fun signUp(phoneNumber: String): Flow<Result<SignUpResponse>>
    fun signInVerify(token: String, verificationCode: String): Flow<Result<SignInVerifyResponse>>
    fun signUpVerify(token: String, verificationCode: String): Flow<Result<SignUpVerifyResponse>>
    fun signInResend(token : String) : Flow<Result<SignInResponse>>
    fun signUpResend(token : String) : Flow<Result<SignUpResponse>>
    fun signOut() : Flow<Result<LogOutResponse>>


    // shared preference
    fun phoneNumber(phoneNumber: String)
    fun phoneNumber() : String
    fun pinCode(pinCode : String)
    fun pinCode() : String
    fun isLanguageUzbek(): Boolean
    fun saveActiveLanguage(isUzbek : Boolean)
    fun signed() : Boolean
}