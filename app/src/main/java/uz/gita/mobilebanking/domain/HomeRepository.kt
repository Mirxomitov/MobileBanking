package uz.gita.mobilebanking.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.ui.BasicInfoData
import uz.gita.mobilebanking.data.model.ui.FullInfoData

interface HomeRepository {
    fun getTotalBalance() : Flow<Result<String>>
    fun getBasicUserInfo() : Flow<Result<BasicInfoData>>
    fun getFullInfo(): Flow<Result<FullInfoData>>
}