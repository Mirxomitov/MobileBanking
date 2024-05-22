package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.ui.FullInfoData
import uz.gita.mobilebanking.domain.HomeRepository
import javax.inject.Inject

class HomeGetFullInfoUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<Result<FullInfoData>> = repository.getFullInfo()
}