package uz.gita.mobilebanking.data.source.remote

import uz.gita.mobilebanking.data.model.BasicInfoData
import uz.gita.mobilebanking.data.model.CardData
import uz.gita.mobilebanking.data.model.FullInfoData
import uz.gita.mobilebanking.data.model.HistoryChildData
import uz.gita.mobilebanking.data.model.TransferHistoryData
import uz.gita.mobilebanking.data.source.remote.api.response.card.CardGetResponse
import uz.gita.mobilebanking.data.source.remote.api.response.home.BasicInfoResponse
import uz.gita.mobilebanking.data.source.remote.api.response.home.FullInfoResponse
import uz.gita.mobilebanking.data.source.remote.api.response.transfer.HistoryChildResponse
import uz.gita.mobilebanking.data.source.remote.api.response.transfer.TransferHistoryResponse

fun CardGetResponse.toCardData() =
    CardData(
        id = id,
        name = name,
        amount = amount,
        owner = owner,
        pan = pan,
        expiredYear = expiredYear,
        expiredMonth = expiredMonth,
        themeType = themeType,
        isVisible = isVisible,
    )

fun HistoryChildResponse.toHistoryChild() =
    HistoryChildData(
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

