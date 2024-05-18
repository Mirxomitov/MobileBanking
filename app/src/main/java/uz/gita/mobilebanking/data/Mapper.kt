package uz.gita.mobilebanking.data

import uz.gita.mobilebanking.data.model.response.card.CardGetResponse
import uz.gita.mobilebanking.data.model.ui.CardData

fun CardGetResponse.toCardData() =
    CardData(
        id = this.id.toString(),
        pan = this.pan,
        expiredYear = "${this.expiredYear}",
        expiredMonth = "${this.expiredMonth}",
        name = this.name,
        money = "${this.amount}"
    )
