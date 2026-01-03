package com.catsoft.adaptivechat.ui.kit.paging

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.paging.CombinedLoadStates
import androidx.paging.ItemSnapshotList
import app.cash.paging.LoadState
import app.cash.paging.LoadStateError
import app.cash.paging.compose.LazyPagingItems
import com.catsoft.adaptivechat.logger.logger

fun LoadState.isLoaded(): Boolean = this is androidx.paging.LoadState.NotLoading && this.endOfPaginationReached

fun CombinedLoadStates.isLoaded(): Boolean = this.refresh.isLoaded() || this.append.isLoaded() || this.prepend.isLoaded()


@Composable
fun <T : Any> SubscribeOnPagerStates(
    list: LazyPagingItems<T>,
    whenLoaded: (ItemSnapshotList<T>) -> Unit = {},
) {
    LaunchedEffect(list.loadState) {
        if (list.loadState.isLoaded()) {
            whenLoaded(list.itemSnapshotList)
        }

        (list.loadState.refresh as? LoadStateError)?.let {
            logger().e { it.error.toString() }
        }

        (list.loadState.append as? LoadStateError)?.let {
            logger().e { it.error.toString() }
        }

        (list.loadState.prepend as? LoadStateError)?.let {
            logger().e { it.error.toString() }
        }
    }
}