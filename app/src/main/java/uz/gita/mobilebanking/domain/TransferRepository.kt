package uz.gita.mobilebanking.domain

import androidx.paging.Pager
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.response.transfer.TransferHistoryResponse

interface TransferRepository {
    suspend fun transfer(type: String = "third-card", senderId: String, receiverPan: String, amount: Int)
            : Result<String>

    suspend fun transferVerify(token: String, code: String): Result<Unit>
    suspend fun getCardOwnerByPan(pan: String): Result<String> // pan is card number with length == 16, get user name
    suspend fun transferResend(token: String): Result<String>
    //suspend fun getTransferHistory(): Pager<Int, TransferHistoryResponse>
    fun getHistory(size: Int, pageCount: Int): Flow<PagingData<TransferHistoryResponse>>
}