package uz.gita.mobilebanking.domain.repositories.impl

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.CardData
import uz.gita.mobilebanking.data.source.local.dao.CardsDao
import uz.gita.mobilebanking.data.source.local.toCardData
import uz.gita.mobilebanking.data.source.remote.api.CardApi
import uz.gita.mobilebanking.data.source.remote.api.request.card.CardAddRequest
import uz.gita.mobilebanking.data.source.remote.api.request.card.UpdateCardRequest
import uz.gita.mobilebanking.data.source.remote.toCardData
import uz.gita.mobilebanking.domain.repositories.CardRepository
import uz.gita.mobilebanking.utils.emitWith
import uz.gita.mobilebanking.utils.network_status.NetworkStatus
import uz.gita.mobilebanking.utils.safetyFlow
import uz.gita.mobilebanking.utils.toResultData
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardApi: CardApi,
    private val gson: Gson,
    private val cardsDao: CardsDao
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
        when {
            NetworkStatus.hasNetwork.value -> {
                cardApi.getCards().toResultData().map { it.map { it.toCardData() } }.emitWith()
            }

            else -> {
                emit(Result.success(cardsDao.getAllCards().map { it.toCardData() }))
            }
        }
    }

    override fun deleteCard(id: String): Flow<Result<Unit>> = safetyFlow {
        cardApi.deleteCard(id).toResultData().map { }
    }

    override fun updateCard(id: Int, name: String, themeType: Int, visible: Boolean): Flow<Result<String>> = safetyFlow{
        cardApi.updateCard(UpdateCardRequest(id, name, themeType, visible))
            .toResultData()
            .onSuccess { getCards() }
            .map { it.message }
            .emitWith()
    }
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