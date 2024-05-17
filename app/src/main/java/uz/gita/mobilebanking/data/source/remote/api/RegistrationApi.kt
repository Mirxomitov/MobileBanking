package uz.gita.mobilebanking.data.source.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.HeaderMap
import uz.gita.mobilebanking.data.model.request.auth.SignInRequest
import uz.gita.mobilebanking.data.model.request.auth.SignInResendRequest
import uz.gita.mobilebanking.data.model.request.auth.SignInVerifyRequest
import uz.gita.mobilebanking.data.model.request.auth.SignUpRequest
import uz.gita.mobilebanking.data.model.request.auth.SignUpResendRequest
import uz.gita.mobilebanking.data.model.request.auth.SignUpVerifyRequest
import uz.gita.mobilebanking.data.model.response.auth.LogOutResponse
import uz.gita.mobilebanking.data.model.response.auth.SignInResponse
import uz.gita.mobilebanking.data.model.response.auth.SignInVerifyResponse
import uz.gita.mobilebanking.data.model.response.auth.SignUpResponse
import uz.gita.mobilebanking.data.model.response.auth.SignUpVerifyResponse

interface RegistrationApi {
    @POST("mobile-bank/v1/auth/sign-in")
    suspend fun signIn(@Body data: SignInRequest): Response<SignInResponse>

    @POST("mobile-bank/v1/auth/sign-up")
    suspend fun signUp(@Body data: SignUpRequest): Response<SignUpResponse>

    @POST("mobile-bank/v1/auth/sign-in/verify")
    suspend fun signInVerify(@Body data: SignInVerifyRequest): Response<SignInVerifyResponse>

    @POST("mobile-bank/v1/auth/sign-up/verify")
    suspend fun signUpVerify(@Body data: SignUpVerifyRequest): Response<SignUpVerifyResponse>

    @POST("mobile-bank/v1/auth/sign-in/resend")
    suspend fun signInResend(@Body data: SignInResendRequest): Response<SignInResponse>

    @POST("mobile-bank/v1/auth/sign-up/resend")
    suspend fun signUpResend(@Body data: SignUpResendRequest): Response<SignUpResponse>

    @POST("mobile-bank/v1/auth/sign-out")
    suspend fun signOut(@HeaderMap headers: Map<String, String>) : Response<LogOutResponse>
}