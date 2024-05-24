package uz.gita.mobilebanking.utils.network_status
import kotlinx.coroutines.flow.MutableStateFlow

object NetworkStatus {
    val hasNetwork = MutableStateFlow(true)
}