package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.ui.BasicInfoData
import uz.gita.mobilebanking.data.source.remote.api.HomeApi
import uz.gita.mobilebanking.domain.HomeRepository
import javax.inject.Inject

class HomeGetBasicInfoUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke() : Flow<Result<BasicInfoData>> = repository.getBasicUserInfo()
}