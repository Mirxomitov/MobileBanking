package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.mobilebanking.domain.CardRepository
import uz.gita.mobilebanking.utils.safetyFlow
import javax.inject.Inject

class DeleteCardUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    operator fun invoke(id: String): Flow<Result<Unit>> = safetyFlow {
        emit(cardRepository.deleteCard(id))
    }
}