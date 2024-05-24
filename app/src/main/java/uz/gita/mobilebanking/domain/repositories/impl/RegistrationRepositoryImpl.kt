package uz.gita.mobilebanking.domain.repositories.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.source.remote.api.request.auth.SignInRequest
import uz.gita.mobilebanking.data.source.remote.api.request.auth.SignInResendRequest
import uz.gita.mobilebanking.data.source.remote.api.request.auth.SignInVerifyRequest
import uz.gita.mobilebanking.data.source.remote.api.request.auth.SignUpRequest
import uz.gita.mobilebanking.data.source.remote.api.request.auth.SignUpResendRequest
import uz.gita.mobilebanking.data.source.remote.api.request.auth.SignUpVerifyRequest
import uz.gita.mobilebanking.data.source.local.pref.SharedPreferenceHelper
import uz.gita.mobilebanking.data.source.remote.api.RegistrationApi
import uz.gita.mobilebanking.domain.repositories.RegistrationRepository
import uz.gita.mobilebanking.utils.emitWith
import uz.gita.mobilebanking.utils.safetyFlow
import uz.gita.mobilebanking.utils.toResultData
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val registrationApi: RegistrationApi,
    private val sharedPreferencesHelper: SharedPreferenceHelper,
) : RegistrationRepository {
    //
    override fun signIn(phoneNumber: String): Flow<Result<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignInResponse>> = safetyFlow {
        registrationApi.signIn(SignInRequest("+998$phoneNumber", phoneNumber))
            .toResultData()
            .emitWith()
    }

    override fun signUp(phoneNumber: String): Flow<Result<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignUpResponse>> = safetyFlow {
        registrationApi.signUp(
            SignUpRequest(
                phone = "+998$phoneNumber",
                password = phoneNumber,
                firstName = "Tohir",
                lastName = "Mirxomitov",
                bornDate = "969822000000",
                gender = "0",
            )
        ).toResultData()
            .emitWith()
    }

    override fun signInVerify(token: String, verificationCode: String): Flow<Result<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignInVerifyResponse>> =
        safetyFlow {
            registrationApi.signInVerify(SignInVerifyRequest(token, verificationCode))
                .toResultData()
                .onSuccess { sharedPreferencesHelper.accessToken(it.accessToken) }
                .onSuccess { sharedPreferencesHelper.refreshToken(it.refreshToken) }
                .emitWith()
        }


    override fun signUpVerify(token: String, verificationCode: String): Flow<Result<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignUpVerifyResponse>> =
        safetyFlow {
            registrationApi.signUpVerify(SignUpVerifyRequest(token, verificationCode))
                .toResultData()
                .onSuccess { sharedPreferencesHelper.accessToken(it.accessToken) }
                .onSuccess { sharedPreferencesHelper.refreshToken(it.refreshToken) }
                .emitWith()
        }

    override fun signInResend(token: String): Flow<Result<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignInResponse>> = safetyFlow {
        registrationApi.signInResend(SignInResendRequest(token))
            .toResultData()
            .emitWith()
    }

    override fun signUpResend(token: String): Flow<Result<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignUpResponse>> = safetyFlow {
        registrationApi.signUpResend(SignUpResendRequest(token))
            .toResultData()
            .emitWith()
    }

    override fun signOut(): Flow<Result<uz.gita.mobilebanking.data.source.remote.api.response.auth.LogOutResponse>> = safetyFlow {
        registrationApi.signOut(
            mapOf(Pair("Authorization", "Bearer ${sharedPreferencesHelper.accessToken()}"))
        )
            .toResultData()
            .onSuccess { sharedPreferencesHelper.clear() }
            .emitWith()
    }

    // sharedPreference -> save and get data
    override fun phoneNumber(phoneNumber: String) = sharedPreferencesHelper.phoneNumber(phoneNumber)
    override fun phoneNumber(): String = sharedPreferencesHelper.phoneNumber()
    override fun pinCode(pinCode: String) = sharedPreferencesHelper.pinCode(pinCode)
    override fun pinCode() = sharedPreferencesHelper.pinCode()
    override fun isLanguageUzbek(): Boolean = sharedPreferencesHelper.isLanguageUzbek()
    override fun saveActiveLanguage(isUzbek: Boolean) = sharedPreferencesHelper.saveActiveLanguage(isUzbek)
    override fun signed() = sharedPreferencesHelper.signed()
}


//override fun signIn(phoneNumber: String): Flow<Result<SignInResponse>> = flow {
////        val response = registrationApi.singIn(SignInRequest("+998$phoneNumber", "qwerty"))
//        val response = registrationApi.singIn(SignInRequest("+998$phoneNumber", phoneNumber))
//
//        if (response.isSuccessful && response.body() != null) {
//            emit(Result.success(response.body()!!))
//        } else {
//            val data = gson.fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
//            emit(Result.failure(Exception(data.message)))
//        }
//    }.flowOn(Dispatchers.IO)
//        .catch { emit(Result.failure(Exception("Unknown exception try catch"))) }