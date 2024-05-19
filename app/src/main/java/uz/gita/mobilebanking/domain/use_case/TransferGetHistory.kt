package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.ui.TransferHistory
import uz.gita.mobilebanking.domain.TransferRepository
import uz.gita.mobilebanking.utils.emitWith
import uz.gita.mobilebanking.utils.safetyFlow
import javax.inject.Inject

class TransferGetHistory @Inject constructor(
    private val transferRepository: TransferRepository
) {
    operator fun invoke(): Flow<Result<TransferHistory>> = safetyFlow {
        emit(transferRepository.getTransferHistory())
    }
}