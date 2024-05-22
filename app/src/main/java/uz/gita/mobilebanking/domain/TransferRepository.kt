package uz.gita.mobilebanking.domain

import androidx.paging.Pager
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.response.transfer.TransferHistoryResponse

interface TransferRepository {
    fun transfer(type: String = "third-card", senderId: String, receiverPan: String, amount: Int)
            : Flow<Result<String>>

    fun transferVerify(token: String, code: String): Flow<Result<Unit>>
    fun getCardOwnerByPan(pan: String): Flow<Result<String>> // pan is card number with length == 16, get user name
    fun transferResend(token: String): Flow<Result<String>>
    //suspend fun getTransferHistory(): Pager<Int, TransferHistoryResponse>
    fun getHistory(size: Int, pageCount: Int): Flow<PagingData<TransferHistoryResponse>>
}