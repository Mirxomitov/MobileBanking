package uz.gita.mobilebanking.presentation.hisotory

import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject

interface HistoryDirection {

}

class HistoryDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : HistoryDirection {

}