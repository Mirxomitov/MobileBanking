package uz.gita.mobilebanking

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.domain.repositories.CardRepository
import uz.gita.mobilebanking.domain.use_case.CardsGetUseCase
import uz.gita.mobilebanking.presentation.splash.SplashScreen
import uz.gita.mobilebanking.ui.theme.MobileBankingTheme
import uz.gita.mobilebanking.utils.MyDataLoader
import uz.gita.mobilebanking.utils.navigation.AppNavigationHandler
import uz.gita.mobilebanking.utils.network_status.ConnectivityLiveData
import uz.gita.mobilebanking.utils.network_status.NetworkStatus
import uz.gita.mobilebanking.utils.toLog
import javax.inject.Inject

@AndroidEntryPoint
@OptIn(ExperimentalMaterialApi::class)
class MainActivity : FragmentActivity() {
    @Inject
    lateinit var handler: AppNavigationHandler

    @Inject
    lateinit var cardRepository: CardRepository

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        MyDataLoader.init(CardsGetUseCase(cardRepository))

        setContent {
            MobileBankingTheme {
                BottomSheetNavigator(
                    sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                ) {
                    Navigator(
//                        screen = AuthScreen()
//                        screen = BottomNavigation()
                        screen = SplashScreen()
//                        screen = BottomNavigation()
//                        screen = ProfileScreen()
                    ) { navigator ->
                        handler.uiNavigator.onEach {
                            it(navigator)
                        }.launchIn(lifecycleScope)
                        CurrentScreen()
                    }
                }
            }
        }
    }

    override fun onResume() {
//        "has network ${ConnectivityLiveData(this).hasActiveObservers()}".myLog()
        val connectivityLiveData = ConnectivityLiveData(this)

        connectivityLiveData.observe(this) { networkState ->
            if (networkState.isConnected) {
                NetworkStatus.hasNetwork.tryEmit(true)
                "networkState connected".toLog()

            } else {
                NetworkStatus.hasNetwork.tryEmit(false)
                "networkState Disconnected".toLog()
            }
        }

        super.onResume()
    }
}