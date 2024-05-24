package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.domain.repositories.CardRepository
import javax.inject.Inject

class CardAddUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    operator fun invoke(cardNumber: String, expirationDate: String): Flow<Result<Unit>> =
        cardRepository.addCard(cardNumber, expirationDate)

}