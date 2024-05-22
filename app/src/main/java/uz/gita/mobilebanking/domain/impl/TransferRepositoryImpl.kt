package uz.gita.mobilebanking.domain.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.PaginationSource
import uz.gita.mobilebanking.data.model.request.transfer.CardOwnerByPanRequest
import uz.gita.mobilebanking.data.model.request.transfer.TransferRequest
import uz.gita.mobilebanking.data.model.request.transfer.TransferResendRequest
import uz.gita.mobilebanking.data.model.request.transfer.TransferVerifyRequest
import uz.gita.mobilebanking.data.model.response.transfer.TransferHistoryResponse
import uz.gita.mobilebanking.data.source.remote.api.TransferApi
import uz.gita.mobilebanking.domain.TransferRepository
import uz.gita.mobilebanking.utils.emitWith
import uz.gita.mobilebanking.utils.safetyFlow
import uz.gita.mobilebanking.utils.toResultData
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
    private val transferApi: TransferApi,
) : TransferRepository {
    override fun transfer(type: String, senderId: String, receiverPan: String, amount: Int): Flow<Result<String>> =
        safetyFlow {
            transferApi.transfer(TransferRequest(type, senderId, receiverPan, amount)).toResultData().map { it.token }
                .emitWith()
        }

    override fun transferVerify(token: String, code: String): Flow<Result<Unit>> = safetyFlow {
        transferApi.transferVerify(TransferVerifyRequest(token, code)).toResultData().map { }
    }

    override fun getCardOwnerByPan(pan: String): Flow<Result<String>> = safetyFlow {
        transferApi.getCardOwnerByPan(CardOwnerByPanRequest(pan)).toResultData().map { it.pan }
    }

    override fun transferResend(token: String): Flow<Result<String>> = safetyFlow {
        transferApi.transferResend(TransferResendRequest(token)).toResultData().map { it.token }
    }

    override fun getHistory(size: Int, pageCount: Int): Flow<PagingData<TransferHistoryResponse>> =
        Pager(
            config = PagingConfig(size),
            pagingSourceFactory = { PaginationSource(transferApi = transferApi) },
        ).flow
}