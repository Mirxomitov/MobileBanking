package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.domain.TransferRepository
import uz.gita.mobilebanking.utils.safetyFlow
import javax.inject.Inject


class TransferVerifyUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) {
    operator fun invoke(
        token: String,
        code: String,
    ): Flow<Result<Unit>> = safetyFlow {
        emit(transferRepository.transferVerify(token, code))
    }
}