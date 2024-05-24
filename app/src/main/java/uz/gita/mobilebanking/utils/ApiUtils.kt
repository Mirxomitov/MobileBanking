package uz.gita.mobilebanking.utils

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import uz.gita.mobilebanking.data.source.remote.api.error.ErrorResponse
import kotlin.experimental.ExperimentalTypeInference

private val gson = Gson()

fun <T> Response<T>.toResultData(): Result<T> {
    val code = code()
    val errorData = gson.fromJson(errorBody()?.string(), ErrorResponse::class.java)

    return when {
        code in 200..299 -> Result.success(body()!!)
        errorData != null -> Result.failure(Exception(errorData.message))
        code in 500..599 -> Result.failure(Exception("Internal server error: ${message()}"))

        else -> Result.failure(Exception("Error from server: code=${code}"))
    }
}

@OptIn(ExperimentalTypeInference::class)
fun <T> safetyFlow(@BuilderInference block: suspend FlowCollector<Result<T>>.() -> Unit): Flow<Result<T>> =
    flow(block)
        .flowOn(Dispatchers.IO)
        .catch { emit(Result.failure(it)) }

context(FlowCollector<Result<T>>)
suspend fun <T> Result<T>.emitWith() {
    emit(this)
}