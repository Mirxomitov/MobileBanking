package uz.gita.mobilebanking.data.model.request

data class SignInVerifyRequest(
    val token : String,
    val code : String,
)
