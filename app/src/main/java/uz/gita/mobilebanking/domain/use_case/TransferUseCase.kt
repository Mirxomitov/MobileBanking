package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.domain.TransferRepository
import uz.gita.mobilebanking.utils.safetyFlow
import javax.inject.Inject


class TransferUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) {
    operator fun invoke(
        senderId: String,
        receiverPan: String,
        amount: Int,
    ): Flow<Result<String>> = safetyFlow {
        emit(transferRepository.transfer(senderId = senderId, receiverPan = receiverPan, amount = amount))
    }
}