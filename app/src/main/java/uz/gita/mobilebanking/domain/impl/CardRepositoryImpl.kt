package uz.gita.mobilebanking.domain.impl

import uz.gita.mobilebanking.data.source.remote.CardApi
import uz.gita.mobilebanking.domain.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardApi: CardApi,
) : CardRepository {

}