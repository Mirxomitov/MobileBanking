package uz.gita.mobilebanking.domain.repositories.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.PaginationSource
import uz.gita.mobilebanking.data.model.HistoryChildData
import uz.gita.mobilebanking.data.model.PayedCardData
import uz.gita.mobilebanking.data.model.TemplateCardData
import uz.gita.mobilebanking.data.source.local.dao.PayedCardsDao
import uz.gita.mobilebanking.data.source.local.dao.TemplateCardsDao
import uz.gita.mobilebanking.data.source.local.toCardData
import uz.gita.mobilebanking.data.source.local.toEntity
import uz.gita.mobilebanking.data.source.local.toPayedCardData
import uz.gita.mobilebanking.data.source.remote.api.TransferApi
import uz.gita.mobilebanking.data.source.remote.api.request.transfer.CardOwnerByPanRequest
import uz.gita.mobilebanking.data.source.remote.api.request.transfer.TransferRequest
import uz.gita.mobilebanking.data.source.remote.api.request.transfer.TransferResendRequest
import uz.gita.mobilebanking.data.source.remote.api.request.transfer.TransferVerifyRequest
import uz.gita.mobilebanking.domain.repositories.TransferRepository
import uz.gita.mobilebanking.utils.emitWith
import uz.gita.mobilebanking.utils.safetyFlow
import uz.gita.mobilebanking.utils.toResultData
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
    private val transferApi: TransferApi,
    private val payedCardsDao: PayedCardsDao,
    private val templateCardsDao: TemplateCardsDao
) : TransferRepository {
    // API
    override fun transfer(type: String, senderId: String, receiverPan: String, amount: Int): Flow<Result<String>> =
        safetyFlow {
            transferApi.transfer(TransferRequest(type, senderId, receiverPan, amount)).toResultData().map { it.token }
                .emitWith()
        }

    override fun transferVerify(token: String, code: String): Flow<Result<Unit>> = safetyFlow {
        transferApi.transferVerify(TransferVerifyRequest(token, code)).toResultData().map { }.emitWith()
    }

    override fun getCardOwnerByPan(pan: String): Flow<Result<String>> = safetyFlow {
        transferApi.getCardOwnerByPan(CardOwnerByPanRequest(pan)).toResultData().map { it.pan }.emitWith()
    }

    override fun transferResend(token: String): Flow<Result<String>> = safetyFlow {
        transferApi.transferResend(TransferResendRequest(token)).toResultData().map { it.token }.emitWith()
    }

    override fun getHistory(size: Int, pageCount: Int): Flow<PagingData<HistoryChildData>> =
        Pager(
            config = PagingConfig(size),
            pagingSourceFactory = { PaginationSource(api = transferApi) },
        ).flow

    // LOCAL
    override fun getLastPayedCards(): List<PayedCardData> {
        return payedCardsDao.all().map { it.toPayedCardData() }
    }

    override fun getTemplateCards(): List<TemplateCardData> {
        return templateCardsDao.all().map { it.toCardData() }
    }

    override fun savePayedCard(data: PayedCardData) {
        payedCardsDao.insert(data.toEntity())
    }
}