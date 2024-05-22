package uz.gita.mobilebanking.data.model.response.base

data class BaseChildResponse<T>(
    val data: BaseResponse<T>?,
    val success: Boolean?,
    val error: ErrorResponse?,
)

data class BaseResponse<T>(
    val pagination: Pagination,
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