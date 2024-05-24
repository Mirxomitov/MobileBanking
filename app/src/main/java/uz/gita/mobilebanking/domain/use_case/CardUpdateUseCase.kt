package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.domain.repositories.CardRepository
import javax.inject.Inject

class CardsUpdateUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    operator fun invoke(id: Int, name: String, themeType: Int, isVisible: Boolean): Flow<Result<String>> =
        cardRepository.updateCard(id, name, themeType, isVisible)
}