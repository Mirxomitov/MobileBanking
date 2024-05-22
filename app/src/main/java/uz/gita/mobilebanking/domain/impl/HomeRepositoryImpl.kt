package uz.gita.mobilebanking.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.mobilebanking.data.model.ui.BasicInfoData
import uz.gita.mobilebanking.data.model.ui.FullInfoData
import uz.gita.mobilebanking.data.source.remote.api.HomeApi
import uz.gita.mobilebanking.data.toBasicInfoData
import uz.gita.mobilebanking.data.toFullInfoData
import uz.gita.mobilebanking.domain.HomeRepository
import uz.gita.mobilebanking.utils.emitWith
import uz.gita.mobilebanking.utils.safetyFlow
import uz.gita.mobilebanking.utils.toResultData
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeApi: HomeApi
) : HomeRepository {
    override fun getTotalBalance(): Flow<Result<String>> = safetyFlow {
        homeApi.getTotalBalance().toResultData().map { it.totalBalance.toString() }.emitWith()
    }

    override fun getBasicUserInfo(): Flow<Result<BasicInfoData>> = flow {
        homeApi.getBasicInfo().toResultData().map { it.toBasicInfoData() }.emitWith()
    }

    override fun getFullInfo(): Flow<Result<FullInfoData>> = flow {
        homeApi.getFullInfo().toResultData().map { it.toFullInfoData() }.emitWith()
    }
}