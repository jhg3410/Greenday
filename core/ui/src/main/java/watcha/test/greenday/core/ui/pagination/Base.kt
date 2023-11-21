package watcha.test.greenday.core.ui.pagination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

internal object BasePageable {

    @Composable
    fun Operate(calculateShouldLoadMore: () -> Boolean, onLoadMore: () -> Unit) {
        val shouldLoadMore by remember {
            derivedStateOf {
                calculateShouldLoadMore()
            }
        }

        LaunchedEffect(key1 = shouldLoadMore) {
            if (shouldLoadMore) {
                onLoadMore()
            }
        }
    }

    fun calculateShouldLoadMore(itemCount: Int, lastVisibleItem: Int, threshold: Int): Boolean {
        return lastVisibleItem >= itemCount - threshold
    }
}