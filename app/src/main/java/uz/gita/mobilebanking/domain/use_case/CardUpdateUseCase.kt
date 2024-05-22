package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.ui.CardData
import uz.gita.mobilebanking.domain.CardRepository
import javax.inject.Inject

class CardsUpdateUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    operator fun invoke(id: Int, name: String, themeType: Int, isVisible: Boolean): Flow<Result<List<CardData>>> =
        cardRepository.updateCard(id, name, themeType, isVisible)
}