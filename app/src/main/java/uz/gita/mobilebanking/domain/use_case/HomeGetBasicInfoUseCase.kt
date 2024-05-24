package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.BasicInfoData
import uz.gita.mobilebanking.domain.repositories.HomeRepository
import javax.inject.Inject

class HomeGetBasicInfoUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke() : Flow<Result<BasicInfoData>> = repository.getBasicUserInfo()
}