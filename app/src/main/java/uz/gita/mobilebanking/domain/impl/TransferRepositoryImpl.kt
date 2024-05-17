package uz.gita.mobilebanking.domain.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.request.transfer.TransferRequest
import uz.gita.mobilebanking.data.model.request.transfer.TransferVerifyRequest
import uz.gita.mobilebanking.data.source.local.SharedPreferenceHelper
import uz.gita.mobilebanking.data.source.remote.api.TransferApi
import uz.gita.mobilebanking.domain.TransferRepository
import uz.gita.mobilebanking.utils.emitWith
import uz.gita.mobilebanking.utils.safetyFlow
import uz.gita.mobilebanking.utils.toResultData
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
    private val transferApi: TransferApi,
    private val sharedPreferenceHelper: SharedPreferenceHelper
) : TransferRepository {
    override fun transfer(type: String, senderId: String, receiverPan: String, amount: Int): Flow<Result<String>> =
        safetyFlow {
            transferApi.transfer(TransferRequest(type, senderId, receiverPan, amount))
                .toResultData()
                .map { it.token }
                .emitWith()
        }

    override fun transferVerify(token: String, code: String): Flow<Result<Unit>> = safetyFlow {
        transferApi.transferVerify(TransferVerifyRequest(token, code))
            .toResultData()
            .map { }
            .emitWith()
    }
}