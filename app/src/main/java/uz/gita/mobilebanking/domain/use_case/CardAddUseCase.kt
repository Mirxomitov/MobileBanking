package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.domain.CardRepository
import uz.gita.mobilebanking.utils.safetyFlow
import javax.inject.Inject

class CardAddUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    operator fun invoke(cardNumber: String, expirationDate: String): Flow<Result<Unit>> = safetyFlow {
        emit(cardRepository.addCard(cardNumber, expirationDate))
    }
}