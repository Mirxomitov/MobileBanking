package uz.gita.mobilebanking.data.source.remote.api.response.base

data class BaseChildResponse<T>(
    val data: uz.gita.mobilebanking.data.source.remote.api.response.base.BaseResponse<T>?,
    val success: Boolean?,
    val error: uz.gita.mobilebanking.data.source.remote.api.response.base.ErrorResponse?,
)

data class BaseResponse<T>(
    val pagination: uz.gita.mobilebanking.data.source.remote.api.response.base.Pagination,
    val list: List<T>
)

data class ErrorResponse(
    val status : String?,
    val name : String?,
    val message : String?,
)

data class Pagination(
    val page : Int, // 1..13
    val pageCount : Int, // 13
    val pageSize : Int, // 10
    val total : Int, // 125
)