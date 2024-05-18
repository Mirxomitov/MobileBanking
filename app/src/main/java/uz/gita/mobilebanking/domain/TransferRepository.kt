package uz.gita.mobilebanking.domain

interface TransferRepository {
    suspend fun transfer(
        type: String = "third-card",
        senderId: String,
        receiverPan: String,
        amount: Int,
    ): Result<String>

    suspend fun transferVerify(token: String, code: String): Result<Unit>

    suspend fun getCardOwnerByPan(pan: String) : Result<String> // pan is card number with length == 16, get user name
}