package uz.gita.mobilebanking.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.ui.CardData


interface CardRepository {
    suspend fun addCard(cardNumber: String, expirationDate: String): Result<Unit>
    suspend fun getCards(): Result<List<CardData>>
    suspend fun deleteCard(id: String): Result<Unit>
}