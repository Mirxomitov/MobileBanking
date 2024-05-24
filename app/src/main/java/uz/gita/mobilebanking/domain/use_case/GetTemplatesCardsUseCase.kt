package uz.gita.mobilebanking.domain.use_case

import uz.gita.mobilebanking.data.model.TemplateCardData
import uz.gita.mobilebanking.domain.repositories.TransferRepository
import javax.inject.Inject

class GetTemplatesCardsUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) {
    operator fun invoke(): List<TemplateCardData> {
        return transferRepository.getTemplateCards()
    }
}