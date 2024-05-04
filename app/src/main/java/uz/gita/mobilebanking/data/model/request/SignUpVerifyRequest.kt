package uz.gita.mobilebanking.data.model.request

data class SignUpVerifyRequest(
    val token : String,
    val code : String,
)
