package watcha.test.greenday.core.ui.pagination

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable

@Composable
fun LazyGridState.Pageable(
    onLoadMore: () -> Unit,
    itemCountProvider: () -> Int,
    threshold: Int = 4,
) {

    fun calculateShouldLoadMore() =
        BasePageable.calculateShouldLoadMore(
            itemCount = itemCountProvider(),
            lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0,
            threshold = threshold
        )

    BasePageable.Operate(
        calculateShouldLoadMore = ::calculateShouldLoadMore,
        onLoadMore = onLoadMore
    )
}