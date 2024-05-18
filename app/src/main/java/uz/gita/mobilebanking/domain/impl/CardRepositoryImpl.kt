package uz.gita.mobilebanking.domain.impl

import com.google.gson.Gson
import uz.gita.mobilebanking.data.model.request.card.CardAddRequest
import uz.gita.mobilebanking.data.model.ui.CardData
import uz.gita.mobilebanking.data.source.remote.api.CardApi
import uz.gita.mobilebanking.data.toCardData
import uz.gita.mobilebanking.domain.CardRepository
import uz.gita.mobilebanking.utils.toResultData
import javax.inject.Inject


class CardRepositoryImpl @Inject constructor(
    private val cardApi: CardApi,
    private val gson: Gson,
) : CardRepository {
    override suspend fun addCard(cardNumber: String, expirationDate: String): Result<Unit> =
        cardApi.addCard(
            CardAddRequest(
                cardNumber,
                "20" + expirationDate.substring(2, 4),
                expirationDate.substring(0, 2).toInt().toString(),
                "Default"
            )
        ).toResultData().map { }

    override suspend fun getCards(): Result<List<CardData>> =
        cardApi.getCards().toResultData().map { it.map { it.toCardData() } }

    override suspend fun deleteCard(id: String): Result<Unit> =
        cardApi.deleteCard(id).toResultData().map { }

}


//    override fun getCards(): Flow<Result<List<CardData>>> = flow {
//        val response = cardApi.getCards()
//
//        if (response.isSuccessful && response.body() != null) {
//            val cards = response.body()!!
//            emit(Result.success(cards.map { it.toCardData() }))
//        } else {
//            val data = gson.fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
//            logger("getCards.isFailure : $data")
//            emit(Result.failure(Exception(data.message)))
//        }
//    }.flowOn(Dispatchers.IO)
//        .catch { emit(Result.failure(Exception("Unknown exception try catch"))) }