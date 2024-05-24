package uz.gita.mobilebanking.presentation.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.model.MarkerData
import uz.gita.mobilebanking.ui.dialogs.LocationDialog

class MapScreen : Screen {
    @Composable
    override fun Content() {
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        val markerList = listOf(
            MarkerData(
                lat = 41.31904027624511, 69.24142262507962,
                image = R.drawable.image_ooo_uzpaynet,
                titleBank = "\"OOO\"UZPAYNET",
                titleBankInfo = "Kompaniya ofisi",
                textLocation = "Furqat ko'chasi 10, 100021, Тоshkent, Toshkent, Узбекистан",
            ),
            MarkerData(
                lat = 41.32589485189752, 69.27586774488323,
                image = R.drawable.image_ooo_uzpaynet,
                titleBank = "Paynet",
                titleBankInfo = "Pullik yo'lda pullik kassa",
                textLocation = "Abdulla Qodiriy ko'chasi 36, Тоshkent, Toshkent, Узбекистан",
            ),
            MarkerData(
                lat = 41.294826809256286, 69.19171528342507,
                image = R.drawable.image_ooo_uzpaynet,
                titleBank = "Paynet",
                titleBankInfo = "Mobil telefonni zaryadlash mashinasi",
                textLocation = "75RV+M3P, 100173, Tashkent, Toshkent Shahri, Узбекистан",
            ),

            MarkerData(
                lat = 41.28830469126177, 69.18416710804408,
                image = R.drawable.image_uzmobil_paynet,
                titleBank = "Uzmobile-Paynet",
                titleBankInfo = "Kompaniya ofisi",
                textLocation = "75PP+997 Фархадский рынок, 100123, Tashkent, Toshkent Shahri, Узбекистан",
            ),
            MarkerData(
                lat = 41.28024206442073,
                lng = 69.2515846880356,
                image = R.drawable.image_llc_uzpaynet,
                titleBank = "\"UZPAYNET\" LLC | Платежная система",
                titleBankInfo = "Paynet",
                textLocation = "Muqimiy ko'chasi 59, Тоshkent, Toshkent, Узбекистан",
            ),

            MarkerData(
                lat = 41.273248795870245, 69.27772122327724,
                image = R.drawable.image_ooo_uzpaynet,
                titleBank = "Paynet - Uzmobile - UMS",
                titleBankInfo = "Telefon kompaniyasi",
                textLocation = "Geydar Aliev ko'chasi 202, Тоshkent, Toshkent, Узбекистан",
            ),

            MarkerData(
                lat = 41.32049735685445, 69.29397531880431,
                image = R.drawable.image_ooo_uzpaynet,
                titleBank = "Paynet",
                titleBankInfo = "Pullik yo'lda pullik kassa",
                textLocation = "Kari Niyazov ko'chasi 10, Тоshkent, Toshkent, Узбекистан",
            ),

            MarkerData(
                lat = 41.32505529592058, 69.30320042044544,
                image = R.drawable.image_paynet_market,
                titleBank = "Paynet",
                titleBankInfo = "Pullik yo'lda pullik kassa",
                textLocation = "Oqqo’rg’on ko'chasi 14, Тоshkent, Toshkent, Узбекистан",
            ),

            MarkerData(
                lat = 41.34811357966962, 69.34592720699375,
                image = R.drawable.image_ooo_uzpaynet,
                titleBank = "PAYNET, КОМПЬЮТЕРНЫЕ УСЛУГИ, АВТОСТРАХОВАНИЕ",
                titleBankInfo = "Kompaniya,offisi",
                textLocation = "88WW+R59, Trotuar, Tashkent, Toshkent Shahri, Узбекистан",
                textTelephoneNumber = "+998 99 877 11 25"
            ),
            MarkerData(
                lat = 41.320519559921834, 69.2966280351533,
                image = R.drawable.image_ooo_uzpaynet,
                titleBank = "Пейнет",
                titleBankInfo = "To'lov punkti - pullik yo'lda sayohat uchun to'lov.",
                textLocation = "879W+QH9, Tashkent, Toshkent Shahri, Узбекистан",
                textTelephoneNumber = "+998 99 877 11 25"
            ),
        )

        MapComponent(
            modifier = Modifier,
            markers = markerList,
            onClick = {
                bottomSheetNavigator.show(
                    LocationDialog(
                        data = it,
                        onHide = { bottomSheetNavigator.hide() }
                    )
                )
            }
        )
    }
}

@Composable
fun MapComponent(
    modifier: Modifier,
    markers: List<MarkerData>,
    onClick: (MarkerData) -> Unit
) {
    GoogleMap(
        cameraPositionState = CameraPositionState(
            position = CameraPosition.fromLatLngZoom(
                LatLng(
                    // 41.280060587936894, 69.25164176571882
                    41.28024206442073,
                    69.2515846880356
                ), 15f
            )
        ),
        modifier = modifier,
    ) {
        markers.forEach { mData ->
            Marker(
                state = MarkerState(
                    position = LatLng(mData.lat, mData.lng)
                ),
                onClick = {
                    onClick(mData)
                    true
                },
                title = mData.titleBank,
            )
        }
    }
}
