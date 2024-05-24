package uz.gita.mobilebanking.domain.repositories

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.source.remote.api.response.auth.LogOutResponse
import uz.gita.mobilebanking.data.source.remote.api.response.auth.SignInResponse
import uz.gita.mobilebanking.data.source.remote.api.response.auth.SignUpResponse
import uz.gita.mobilebanking.data.source.remote.api.response.auth.SignInVerifyResponse
import uz.gita.mobilebanking.data.source.remote.api.response.auth.SignUpVerifyResponse

interface RegistrationRepository {
    fun signIn(phoneNumber: String): Flow<Result<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignInResponse>>
    fun signUp(phoneNumber: String): Flow<Result<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignUpResponse>>
    fun signInVerify(token: String, verificationCode: String): Flow<Result<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignInVerifyResponse>>
    fun signUpVerify(token: String, verificationCode: String): Flow<Result<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignUpVerifyResponse>>
    fun signInResend(token : String) : Flow<Result<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignInResponse>>
    fun signUpResend(token : String) : Flow<Result<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignUpResponse>>
    fun signOut() : Flow<Result<uz.gita.mobilebanking.data.source.remote.api.response.auth.LogOutResponse>>


    // shared preference
    fun phoneNumber(phoneNumber: String)
    fun phoneNumber() : String
    fun pinCode(pinCode : String)
    fun pinCode() : String
    fun isLanguageUzbek(): Boolean
    fun saveActiveLanguage(isUzbek : Boolean)
    fun signed() : Boolean
}