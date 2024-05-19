package uz.gita.mobilebanking.domain.impl

import uz.gita.mobilebanking.data.model.request.transfer.CardOwnerByPanRequest
import uz.gita.mobilebanking.data.model.request.transfer.TransferRequest
import uz.gita.mobilebanking.data.model.request.transfer.TransferResendRequest
import uz.gita.mobilebanking.data.model.request.transfer.TransferVerifyRequest
import uz.gita.mobilebanking.data.model.ui.TransferHistory
import uz.gita.mobilebanking.data.source.remote.api.TransferApi
import uz.gita.mobilebanking.data.toTransferHistory
import uz.gita.mobilebanking.domain.TransferRepository
import uz.gita.mobilebanking.utils.toResultData
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
    private val transferApi: TransferApi,
) : TransferRepository {
    override suspend fun transfer(type: String, senderId: String, receiverPan: String, amount: Int): Result<String> =
        transferApi.transfer(TransferRequest(type, senderId, receiverPan, amount)).toResultData().map { it.token }

    override suspend fun transferVerify(token: String, code: String): Result<Unit> =
        transferApi.transferVerify(TransferVerifyRequest(token, code)).toResultData().map { }

    override suspend fun getCardOwnerByPan(pan: String): Result<String> =
        transferApi.getCardOwnerByPan(CardOwnerByPanRequest(pan)).toResultData().map { it.pan }

    override suspend fun transferResend(token: String): Result<String> =
        transferApi.transferResend(TransferResendRequest(token)).toResultData().map { it.token }

    override suspend fun getTransferHistory(): Result<TransferHistory> =
        transferApi.getHistory(6, 1).toResultData().map { it.toTransferHistory() }
}