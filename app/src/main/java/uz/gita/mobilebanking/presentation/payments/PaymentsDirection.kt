package uz.gita.mobilebanking.presentation.payments

import uz.gita.mobilebanking.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface PaymentsDirection {

}

@Singleton
class PaymentsDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : PaymentsDirection {

}