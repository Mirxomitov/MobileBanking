package uz.gita.mobilebanking.domain.impl

import uz.gita.mobilebanking.data.source.remote.api.CardApi
import uz.gita.mobilebanking.domain.CardRepository
import uz.gita.mobilebanking.utils.logger
import javax.inject.Inject


class CardRepositoryImpl @Inject constructor(
    private val cardApi: CardApi,
) : CardRepository {
    override fun addCard(cardNumber : String, expirationDate : String) {
        logger("CardRepositoryImpl.addCard()")
    }
}