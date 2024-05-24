package uz.gita.mobilebanking.domain.use_case

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.HistoryChildData
import uz.gita.mobilebanking.domain.repositories.TransferRepository
import javax.inject.Inject

class TransferGetHistoryUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) {
    operator fun invoke(size: Int, pageCount: Int): Flow<PagingData<HistoryChildData>> =
        transferRepository.getHistory(size, pageCount)
}