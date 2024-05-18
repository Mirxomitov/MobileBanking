package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.ui.CardData
import uz.gita.mobilebanking.domain.CardRepository
import uz.gita.mobilebanking.utils.safetyFlow
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    operator fun invoke(): Flow<Result<List<CardData>>> = safetyFlow {
        emit(cardRepository.getCards())
    }
}