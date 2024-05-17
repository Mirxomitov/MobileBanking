package uz.gita.mobilebanking.data.model.request.auth

data class SignInVerifyRequest(
    val token : String,
    val code : String,
)
