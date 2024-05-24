package uz.gita.mobilebanking.data.source.remote.api.request.auth

data class SignUpVerifyRequest(
    val token : String,
    val code : String,
)
