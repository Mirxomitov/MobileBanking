package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.domain.TransferRepository
import uz.gita.mobilebanking.utils.safetyFlow
import javax.inject.Inject

class TransferResendUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) {
    operator fun invoke(token : String): Flow<Result<String>> = safetyFlow {
        emit(transferRepository.transferResend(token))
    }
}