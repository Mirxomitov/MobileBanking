package uz.gita.mobilebanking.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.ui.CardData


interface CardRepository {
    fun addCard(cardNumber: String, expirationDate: String): Flow<Result<Unit>>
    fun getCards(): Flow<Result<List<CardData>>>
    fun deleteCard(id: String): Flow<Result<Unit>>
    fun updateCard(id: Int, name: String, themeType: Int, visible: Boolean): Flow<Result<List<CardData>>>
}