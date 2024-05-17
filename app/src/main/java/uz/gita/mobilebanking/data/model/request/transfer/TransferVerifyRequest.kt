package uz.gita.mobilebanking.data.model.request.transfer

data class TransferVerifyRequest(
    val token: String,
    val code: String,
)
