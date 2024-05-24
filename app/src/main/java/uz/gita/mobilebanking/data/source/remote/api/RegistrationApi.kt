package uz.gita.mobilebanking.data.source.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.HeaderMap
import uz.gita.mobilebanking.data.source.remote.api.request.auth.SignInRequest
import uz.gita.mobilebanking.data.source.remote.api.request.auth.SignInResendRequest
import uz.gita.mobilebanking.data.source.remote.api.request.auth.SignInVerifyRequest
import uz.gita.mobilebanking.data.source.remote.api.request.auth.SignUpRequest
import uz.gita.mobilebanking.data.source.remote.api.request.auth.SignUpResendRequest
import uz.gita.mobilebanking.data.source.remote.api.request.auth.SignUpVerifyRequest
import uz.gita.mobilebanking.data.source.remote.api.response.auth.LogOutResponse
import uz.gita.mobilebanking.data.source.remote.api.response.auth.SignInResponse
import uz.gita.mobilebanking.data.source.remote.api.response.auth.SignInVerifyResponse
import uz.gita.mobilebanking.data.source.remote.api.response.auth.SignUpResponse
import uz.gita.mobilebanking.data.source.remote.api.response.auth.SignUpVerifyResponse

interface RegistrationApi {
    @POST("mobile-bank/v1/auth/sign-in")
    suspend fun signIn(@Body data: SignInRequest): Response<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignInResponse>

    @POST("mobile-bank/v1/auth/sign-up")
    suspend fun signUp(@Body data: SignUpRequest): Response<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignUpResponse>

    @POST("mobile-bank/v1/auth/sign-in/verify")
    suspend fun signInVerify(@Body data: SignInVerifyRequest): Response<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignInVerifyResponse>

    @POST("mobile-bank/v1/auth/sign-up/verify")
    suspend fun signUpVerify(@Body data: SignUpVerifyRequest): Response<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignUpVerifyResponse>

    @POST("mobile-bank/v1/auth/sign-in/resend")
    suspend fun signInResend(@Body data: SignInResendRequest): Response<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignInResponse>

    @POST("mobile-bank/v1/auth/sign-up/resend")
    suspend fun signUpResend(@Body data: SignUpResendRequest): Response<uz.gita.mobilebanking.data.source.remote.api.response.auth.SignUpResponse>

    @POST("mobile-bank/v1/auth/sign-out")
    suspend fun signOut(@HeaderMap headers: Map<String, String>) : Response<uz.gita.mobilebanking.data.source.remote.api.response.auth.LogOutResponse>
}