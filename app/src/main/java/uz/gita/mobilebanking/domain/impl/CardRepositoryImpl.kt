package uz.gita.mobilebanking.domain.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mobilebanking.data.model.error.ErrorResponse
import uz.gita.mobilebanking.data.model.request.card.CardAddRequest
import uz.gita.mobilebanking.data.model.ui.CardData
import uz.gita.mobilebanking.data.source.remote.api.CardApi
import uz.gita.mobilebanking.data.toCardData
import uz.gita.mobilebanking.domain.CardRepository
import uz.gita.mobilebanking.utils.emitWith
import uz.gita.mobilebanking.utils.logger
import uz.gita.mobilebanking.utils.safetyFlow
import uz.gita.mobilebanking.utils.toResultData
import javax.inject.Inject


class CardRepositoryImpl @Inject constructor(
    private val cardApi: CardApi,
    private val gson: Gson,
) : CardRepository {
    override fun addCard(cardNumber: String, expirationDate: String): Flow<Result<Unit>> = safetyFlow {
        cardApi.addCard(
            CardAddRequest(
                cardNumber,
                "20" + expirationDate.substring(2, 4),
                expirationDate.substring(0, 2).toInt().toString(),
                "Default"
            )
        ).toResultData()
            .map { }
            .emitWith()
    }


    override fun getCards(): Flow<Result<List<CardData>>> = safetyFlow {
        cardApi.getCards()
            .toResultData()
            .map { it.map { it.toCardData() } }
            .emitWith()
    }

    override fun deleteCard(id: String): Flow<Result<Unit>> = flow {
        val response = cardApi.deleteCard(id)

        if (response.isSuccessful && response.body() != null) {
            logger("CardRepository.deleteCard.success")
            emit(Result.success(Unit))
        } else {
            val data = gson.fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
            logger("getCards.isFailure : $data")
            emit(Result.failure(Exception(data.message)))
        }
    }.flowOn(Dispatchers.IO)
        .catch { emit(Result.failure(Exception("Unknown exception try catch"))) }
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