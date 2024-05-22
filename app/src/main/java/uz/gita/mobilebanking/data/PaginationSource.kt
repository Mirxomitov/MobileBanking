package uz.gita.mobilebanking.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.gita.mobilebanking.data.model.response.transfer.TransferHistoryResponse
import uz.gita.mobilebanking.data.source.remote.api.TransferApi

class PaginationSource(private val transferApi: TransferApi) : PagingSource<Int, TransferHistoryResponse>() {
    override fun getRefreshKey(state: PagingState<Int, TransferHistoryResponse>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1) // TODO why plus 1? should be minus 1
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TransferHistoryResponse> {
        val page = params.key ?: 1
        val result = transferApi.getHistory(10, page)
        val body = result.body()

        return if (result.isSuccessful && body != null) {
            val mPage = body.data?.pagination?.page

            LoadResult.Page(
                data = body.data?.list ?: listOf(),
                prevKey = if (mPage != null && mPage != 1) page.minus(1) else null,
                nextKey = if (mPage != null && mPage > page) page.plus(1) else null,
            )
        } else LoadResult.Error(Exception("PaginationSource.LoadResult.Error"))
    }
}