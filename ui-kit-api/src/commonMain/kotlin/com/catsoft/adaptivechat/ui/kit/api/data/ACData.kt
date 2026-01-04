@file:Suppress("UNCHECKED_CAST")

package com.catsoft.adaptivechat.ui.kit.api.data

import com.catsoft.adaptivechat.ui.kit.api.serializers.ThrowableSerializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.Serializable

@Serializable
data class ACData<T>(
    val content: T? = null,
    val isLoading: Boolean = false,
    @Serializable(with = ThrowableSerializer::class)
    val error: Throwable? = null,
) {
    companion object Companion {
        fun <Y> loading(): ACData<Y> = ACData(isLoading = true)
    }
}

fun <T> T.toData(isLoading: Boolean = false, error: Throwable? = null): ACData<T> = ACData(this, isLoading, error)

fun <T> Flow<ACData<T>>.mapContent(update: (T) -> T): Flow<ACData<T>> = this.map {
    it.copy(content = it.content?.let { content -> update(content) })
}

fun <T, B> ACData<T>.mapContent(update: T.() -> B): ACData<B> = ACData(
    content = content?.let { update(it) },
    isLoading = isLoading,
    error = error
)

fun <T> Flow<ACData<T>>.filterNotNullContent(): Flow<ACData<T>> = this.filter { it.content != null }

fun <T> Flow<ACData<T>>.mapToContent(): Flow<T?> = this.map { it.content }

fun <T> Flow<ACData<T>>.mapToNotNullContent() = this.mapNotNull { it.content }

suspend fun <T> Flow<ACData<T>>.firstContent() = this.filter { !it.isLoading }.map { it.content }.first()

fun <T> ACData<T>.copy(update: T.() -> T): ACData<T> = this.copy(content = content?.update())

fun <T> Flow<ACData<T>>.getRawData(): Flow<T> = this.filterNotNullContent().mapToContent().mapNotNull { it }


fun <T1, T2> combine(
    data1: ACData<T1>,
    data2: ACData<T2>,
): ACData<Pair<T1, T2>> {
    return combine(data1, data2) { data1.content to data2.content } as ACData<Pair<T1, T2>>
}

fun <T1, T2, T3> combine(
    data1: ACData<T1>,
    data2: ACData<T2>,
    data3: ACData<T3>,
): ACData<Triple<T1, T2, T3>> {
    return combine(data1, data2, data3) { Triple(data1.content, data2.content, data3.content) } as ACData<Triple<T1, T2, T3>>
}

fun <T1, T2, T3, T4> combine(
    data1: ACData<T1>,
    data2: ACData<T2>,
    data3: ACData<T3>,
    data4: ACData<T4>,
): ACData<Quadruple<T1, T2, T3, T4>> {
    return combine(data1, data2, data3) { Quadruple(data1.content, data2.content, data3.content, data4.content) } as ACData<Quadruple<T1, T2, T3, T4>>
}

private fun combine(
    vararg datas: ACData<*>,
    getCombinedData: () -> Any
): ACData<*> {
    val isLoading = datas.any { it.isLoading }
    val error = datas.firstOrNull { it.error != null }?.error
    val content = getCombinedData()

    return ACData(content, isLoading, error)
}
