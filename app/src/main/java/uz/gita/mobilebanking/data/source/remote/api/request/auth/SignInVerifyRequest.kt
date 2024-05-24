package uz.gita.mobilebanking.data.source.remote.api.request.auth

data class SignInVerifyRequest(
    val token : String,
    val code : String,
)
