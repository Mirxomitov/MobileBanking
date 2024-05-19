package uz.gita.mobilebanking.data

import uz.gita.mobilebanking.data.model.response.card.CardGetResponse
import uz.gita.mobilebanking.data.model.response.transfer.HistoryChildResponse
import uz.gita.mobilebanking.data.model.response.transfer.TransferHistoryResponse
import uz.gita.mobilebanking.data.model.ui.CardData
import uz.gita.mobilebanking.data.model.ui.HistoryChild
import uz.gita.mobilebanking.data.model.ui.TransferHistory

fun CardGetResponse.toCardData() =
    CardData(
        id = this.id.toString(),
        pan = this.pan,
        expiredYear = "${this.expiredYear}",
        expiredMonth = "${this.expiredMonth}",
        name = this.name,
        money = "${this.amount}"
    )


fun HistoryChildResponse.toHistoryChild() =
    HistoryChild(
        amount = amount,
        from = from,
        time = time,
        to = to,
        type = type
    )

fun TransferHistoryResponse.toTransferHistory() = TransferHistory(
    child = child.map { it.toHistoryChild() },
    currentPage = currentPage,
    totalElements = totalElements,
    totalPages = totalPages
)