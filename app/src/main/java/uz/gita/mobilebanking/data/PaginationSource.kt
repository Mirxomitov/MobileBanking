package uz.gita.mobilebanking.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.gita.mobilebanking.data.model.HistoryChildData
import uz.gita.mobilebanking.data.source.remote.api.TransferApi
import uz.gita.mobilebanking.data.source.remote.toHistoryChild
import uz.gita.mobilebanking.utils.toResultData

class PaginationSource(private val api: TransferApi) : PagingSource<Int, HistoryChildData>() {

    override fun getRefreshKey(state: PagingState<Int, HistoryChildData>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1) ?: state.closestPageToPosition(
                anchor
            )?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HistoryChildData> {
        val page = params.key ?: 1
        api.getHistory(10, page)
            .toResultData()
            .onSuccess {
                return LoadResult.Page(
                    data = it.child.map { it.toHistoryChild() },
                    nextKey = if (it.totalPages > page) page.plus(1) else null,
                    prevKey = if (page > 1) page.minus(1) else null
                )
            }
            .onFailure {
                return LoadResult.Error(
                    it
                )
            }
        return LoadResult.Error(
            Exception("Unknown Error!!")
        )
    }

}