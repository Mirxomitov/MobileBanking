package uz.gita.mobilebanking.domain.use_case

import uz.gita.mobilebanking.data.model.CardData
import uz.gita.mobilebanking.data.model.PayedCardData
import uz.gita.mobilebanking.domain.repositories.CardRepository
import uz.gita.mobilebanking.domain.repositories.TransferRepository
import javax.inject.Inject

class SavePayedCard @Inject constructor(
    private val transferRepository: TransferRepository
) {
    operator fun invoke(data: PayedCardData) {
        transferRepository.savePayedCard(data)
    }
}