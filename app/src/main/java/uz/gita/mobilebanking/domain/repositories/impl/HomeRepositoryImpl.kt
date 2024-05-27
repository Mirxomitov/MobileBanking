package uz.gita.mobilebanking.domain.repositories.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.model.BasicInfoData
import uz.gita.mobilebanking.data.model.FullInfoData
import uz.gita.mobilebanking.data.source.local.pref.SharedPreferenceHelper
import uz.gita.mobilebanking.data.source.remote.api.HomeApi
import uz.gita.mobilebanking.data.source.remote.toBasicInfoData
import uz.gita.mobilebanking.data.source.remote.toFullInfoData
import uz.gita.mobilebanking.domain.repositories.HomeRepository
import uz.gita.mobilebanking.utils.emitWith
import uz.gita.mobilebanking.utils.network_status.NetworkStatus
import uz.gita.mobilebanking.utils.safetyFlow
import uz.gita.mobilebanking.utils.toResultData
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeApi: HomeApi,
    private val pref: SharedPreferenceHelper
) : HomeRepository {
    override fun getTotalBalance(): Flow<Result<String>> = safetyFlow {
        when {
            NetworkStatus.hasNetwork.value ->
                homeApi.getTotalBalance().toResultData().map { it.totalBalance.toString() }.emitWith()

            else ->
                Result.success(pref.totalBalance())

        }
    }

    override fun getBasicUserInfo(): Flow<Result<BasicInfoData>> = safetyFlow {
        homeApi.getBasicInfo().toResultData().map { it.toBasicInfoData() }.emitWith()
    }

    override fun getFullInfo(): Flow<Result<FullInfoData>> = safetyFlow {
        homeApi.getFullInfo().toResultData().map { it.toFullInfoData() }.emitWith()
    }
}