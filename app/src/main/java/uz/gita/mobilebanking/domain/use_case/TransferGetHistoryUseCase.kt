package uz.gita.mobilebanking.domain.use_case

import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.response.transfer.TransferHistoryResponse
import uz.gita.mobilebanking.data.model.ui.TransferHistoryData
import uz.gita.mobilebanking.domain.TransferRepository
import javax.inject.Inject

class TransferGetHistoryUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) {
    operator fun invoke(size: Int, pageCount: Int): Flow<PagingData<TransferHistoryResponse>> =
        transferRepository.getHistory(size, pageCount)
}