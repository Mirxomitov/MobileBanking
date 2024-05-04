package uz.gita.mobilebanking.domain

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mobilebanking.data.model.error.ErrorResponse
import uz.gita.mobilebanking.data.model.request.SignInRequest
import uz.gita.mobilebanking.data.model.request.SignInVerifyRequest
import uz.gita.mobilebanking.data.model.request.SignUpRequest
import uz.gita.mobilebanking.data.model.request.SignUpVerifyRequest
import uz.gita.mobilebanking.data.model.response.SignInResponse
import uz.gita.mobilebanking.data.model.response.SignInVerifyResponse
import uz.gita.mobilebanking.data.model.response.SignUpResponse
import uz.gita.mobilebanking.data.model.response.SignUpVerifyResponse
import uz.gita.mobilebanking.data.source.local.SharedPreferenceHelper
import uz.gita.mobilebanking.data.source.remote.RegistrationApi
import uz.gita.mobilebanking.utils.logger
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val api: RegistrationApi,
    private val sharedPreferencesHelper: SharedPreferenceHelper,
    private val gson: Gson
) : RegistrationRepository {

    override fun signIn(phoneNumber: String): Flow<Result<SignInResponse>> = flow {
        val response = api.singIn(SignInRequest("+998$phoneNumber", phoneNumber))

        if (response.isSuccessful && response.body() != null) {
            emit(Result.success(response.body()!!))
        } else {
            val data = gson.fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
            emit(Result.failure(Exception(data.message)))
        }
    }.flowOn(Dispatchers.IO)
        .catch { emit(Result.failure(Exception("Unknown exception try catch"))) }

    override fun signUp(phoneNumber: String): Flow<Result<SignUpResponse>> = flow {
        val response = api.signUp(
            SignUpRequest(
                phone = "+998$phoneNumber",
                password = phoneNumber,
                firstName = "Tohir",
                lastName = "Mirxomitov",
                bornDate = "969822000000",
                gender = "0",
            )
        )

        if (response.isSuccessful && response.body() != null) {
            emit(Result.success(response.body()!!))
        } else {
            val data = gson.fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
            emit(Result.failure(Exception(data.message)))
        }
    }.flowOn(Dispatchers.IO)
        .catch { emit(Result.failure(Exception("Unknown exception try catch"))) }


    override fun signInVerify(token: String, verificationCode: String): Flow<Result<SignInVerifyResponse>> = flow {
        logger("Repository.VerifySIGNIN.token=$token\tcode=$verificationCode")
        val response = api.signInVerify(SignInVerifyRequest(token, verificationCode))

        if (response.isSuccessful && response.body() != null) {
            emit(Result.success(response.body()!!))
        } else {
            val data = gson.fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
            emit(Result.failure(Exception(data.message)))
        }
    }.flowOn(Dispatchers.IO)
        .catch { emit(Result.failure(Exception("Unknown exception try catch"))) }

    override fun signUpVerify(token: String, verificationCode: String): Flow<Result<SignUpVerifyResponse>> = flow {
        logger("Repository.VerifySIGNUP.token=$token\tcode=$verificationCode")
        val response = api.signUpVerify(SignUpVerifyRequest(token, verificationCode))

        if (response.isSuccessful && response.body() != null) {
            emit(Result.success(response.body()!!))
        } else {
            val data = gson.fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
            emit(Result.failure(Exception(data.message)))
        }
    }.flowOn(Dispatchers.IO)
        .catch { emit(Result.failure(Exception("Unknown exception try catch"))) }


    // sharedPreference
    override fun phoneNumber(phoneNumber: String) = sharedPreferencesHelper.phoneNumber(phoneNumber)
    override fun phoneNumber(): String = sharedPreferencesHelper.phoneNumber()
    override fun isLanguageUzbek(): Boolean = sharedPreferencesHelper.isLanguageUzbek()
    override fun saveActiveLanguage(isUzbek: Boolean) = sharedPreferencesHelper.saveActiveLanguage(isUzbek)
}