package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.domain.TransferRepository
import uz.gita.mobilebanking.utils.safetyFlow
import javax.inject.Inject


class CardGetOwnerByPan @Inject constructor(
    private val transferRepository: TransferRepository
) {
    operator fun invoke(pan: String): Flow<Result<String>> = transferRepository.getCardOwnerByPan(pan)
}