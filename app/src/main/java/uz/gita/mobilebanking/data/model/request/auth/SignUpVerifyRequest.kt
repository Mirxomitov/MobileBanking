package uz.gita.mobilebanking.data.model.request.auth

data class SignUpVerifyRequest(
    val token : String,
    val code : String,
)
