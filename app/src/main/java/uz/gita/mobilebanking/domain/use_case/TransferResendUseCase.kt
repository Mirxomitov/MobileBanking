package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.domain.repositories.TransferRepository
import javax.inject.Inject

class TransferResendUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) {
    operator fun invoke(token: String): Flow<Result<String>> = transferRepository.transferResend(token)

}