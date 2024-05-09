package uz.gita.mobilebanking.domain

interface CardRepository {
    fun addCard(cardNumber: String, expirationDate: String)
}