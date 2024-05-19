package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.domain.CardRepository
import uz.gita.mobilebanking.utils.safetyFlow
import javax.inject.Inject

class CardDeleteUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    operator fun invoke(id: String): Flow<Result<Unit>> = safetyFlow {
        emit(cardRepository.deleteCard(id))
    }
}