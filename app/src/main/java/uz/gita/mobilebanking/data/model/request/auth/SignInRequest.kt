package uz.gita.mobilebanking.data.model.request.auth

data class SignInRequest(
    val phone: String,
    val password: String,
)