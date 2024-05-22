package uz.gita.mobilebanking.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.domain.HomeRepository
import javax.inject.Inject

class HomeGetTotalBalanceUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(): Flow<Result<String>> = homeRepository.getTotalBalance()
}