package uz.gita.mobilebanking.data.source.remote.api.request.transfer

data class TransferVerifyRequest(
    val token: String,
    val code: String,
)
