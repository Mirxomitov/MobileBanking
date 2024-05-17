package uz.gita.mobilebanking.domain

import kotlinx.coroutines.flow.Flow

interface TransferRepository {
    fun transfer(
        type: String = "third-card",
        senderId: String,
        receiverPan: String,
        amount: Int,
    ) : Flow<Result<String>>
    fun transferVerify(token: String, code: String): Flow<Result<Unit>>
}