package uz.gita.mobilebanking.domain.use_case

import uz.gita.mobilebanking.domain.CardRepository
import javax.inject.Inject

class CardDeleteUseCase @Inject constructor(
    private val repo: CardRepository
) {
    operator fun invoke(id: String) = repo.deleteCard(id)
}