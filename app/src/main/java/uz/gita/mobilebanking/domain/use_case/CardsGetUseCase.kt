package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.CardData
import uz.gita.mobilebanking.domain.repositories.CardRepository
import javax.inject.Inject

class CardsGetUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    operator fun invoke(): Flow<Result<List<CardData>>> =
        cardRepository.getCards()
}