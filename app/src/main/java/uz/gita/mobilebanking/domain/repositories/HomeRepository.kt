package uz.gita.mobilebanking.domain.repositories

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.BasicInfoData
import uz.gita.mobilebanking.data.model.FullInfoData

interface HomeRepository {
    fun getTotalBalance() : Flow<Result<String>>
    fun getBasicUserInfo() : Flow<Result<BasicInfoData>>
    fun getFullInfo(): Flow<Result<FullInfoData>>
}