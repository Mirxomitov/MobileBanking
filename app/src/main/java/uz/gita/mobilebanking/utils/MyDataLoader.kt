package uz.gita.mobilebanking.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.mobilebanking.data.model.CardData
import uz.gita.mobilebanking.domain.use_case.CardsGetUseCase

object MyDataLoader {
    private lateinit var getAllCardsUseCase: CardsGetUseCase
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    val loadStateFlow = MutableStateFlow(false)
    val listStateFlow = MutableStateFlow(value = listOf<CardData>())
    val totalAmountStateFlow = MutableStateFlow(0L)
    val errorStateFlow = MutableStateFlow("")

    fun init(useCase: CardsGetUseCase) {
        getAllCardsUseCase = useCase
    }

    fun loadCardsData() {
        scope.launch {
            loadStateFlow.emit(true)
            getAllCardsUseCase().onEach { result ->
                result.onSuccess { list ->
                    var amount = 0L
                    list.forEach { amount += it.amount }
                    scope.launch {
                        loadStateFlow.emit(false)
                        totalAmountStateFlow.emit(amount)
                        listStateFlow.emit(list)
                    }
                }
                result.onFailure {
                    scope.launch {
                        loadStateFlow.emit(false)
                        errorStateFlow.emit(it.message.toString())
                    }
                }
            }.launchIn(scope)
        }
    }
}