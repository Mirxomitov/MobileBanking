package uz.gita.mobilebanking.domain.use_case

import uz.gita.mobilebanking.data.model.PayedCardData
import uz.gita.mobilebanking.domain.repositories.TransferRepository
import javax.inject.Inject

class   GetPayedCardsUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) {
    operator fun invoke(): List<PayedCardData> {
        return transferRepository.getLastPayedCards()
    }
}