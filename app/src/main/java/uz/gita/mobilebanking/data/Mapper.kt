package uz.gita.mobilebanking.data

import uz.gita.mobilebanking.data.model.response.card.CardGetResponse
import uz.gita.mobilebanking.data.model.response.home.BasicInfoResponse
import uz.gita.mobilebanking.data.model.response.home.FullInfoResponse
import uz.gita.mobilebanking.data.model.response.transfer.HistoryChildResponse
import uz.gita.mobilebanking.data.model.response.transfer.TransferHistoryResponse
import uz.gita.mobilebanking.data.model.ui.BasicInfoData
import uz.gita.mobilebanking.data.model.ui.CardData
import uz.gita.mobilebanking.data.model.ui.FullInfoData
import uz.gita.mobilebanking.data.model.ui.TransferHistoryChildData
import uz.gita.mobilebanking.data.model.ui.TransferHistoryData

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
    TransferHistoryChildData(
        amount = amount,
        from = from,
        time = time,
        to = to,
        type = type
    )

fun TransferHistoryResponse.toTransferHistory() = TransferHistoryData(
    child = child.map { it.toHistoryChild() },
    currentPage = currentPage,
    totalElements = totalElements,
    totalPages = totalPages
)

fun BasicInfoResponse.toBasicInfoData() = BasicInfoData(firstName = firstName, genderType = genderType, age = age)

fun FullInfoResponse.toFullInfoData() =
    FullInfoData(bornDate = bornDate, firstName = firstName, gender = gender, lastName = lastName, phone = phone)