package uz.gita.mobilebanking.data.source.remote.api.request.auth

data class SignInRequest(
    val phone: String,
    val password: String,
)