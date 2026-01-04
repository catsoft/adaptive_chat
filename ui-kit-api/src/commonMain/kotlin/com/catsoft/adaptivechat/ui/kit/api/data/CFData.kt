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
data class CFData<T>(
    val content: T? = null,
    val isLoading: Boolean = false,
    @Serializable(with = ThrowableSerializer::class)
    val error: Throwable? = null,
) {
    companion object {
        fun <Y> loading(): CFData<Y> = CFData(isLoading = true)
    }
}

fun <T> T.toData(isLoading: Boolean = false, error: Throwable? = null): CFData<T> = CFData(this, isLoading, error)

fun <T> Flow<CFData<T>>.mapContent(update: (T) -> T): Flow<CFData<T>> = this.map {
    it.copy(content = it.content?.let { content -> update(content) })
}

fun <T, B> CFData<T>.mapContent(update: T.() -> B): CFData<B> = CFData(
    content = content?.let { update(it) },
    isLoading = isLoading,
    error = error
)

fun <T> Flow<CFData<T>>.filterNotNullContent(): Flow<CFData<T>> = this.filter { it.content != null }

fun <T> Flow<CFData<T>>.mapToContent(): Flow<T?> = this.map { it.content }

fun <T> Flow<CFData<T>>.mapToNotNullContent() = this.mapNotNull { it.content }

suspend fun <T> Flow<CFData<T>>.firstContent() = this.filter { !it.isLoading }.map { it.content }.first()

fun <T> CFData<T>.copy(update: T.() -> T): CFData<T> = this.copy(content = content?.update())

fun <T> Flow<CFData<T>>.getRawData(): Flow<T> = this.filterNotNullContent().mapToContent().mapNotNull { it }


fun <T1, T2> combine(
    data1: CFData<T1>,
    data2: CFData<T2>,
): CFData<Pair<T1, T2>> {
    return combine(data1, data2) { data1.content to data2.content } as CFData<Pair<T1, T2>>
}

fun <T1, T2, T3> combine(
    data1: CFData<T1>,
    data2: CFData<T2>,
    data3: CFData<T3>,
): CFData<Triple<T1, T2, T3>> {
    return combine(data1, data2, data3) { Triple(data1.content, data2.content, data3.content) } as CFData<Triple<T1, T2, T3>>
}

fun <T1, T2, T3, T4> combine(
    data1: CFData<T1>,
    data2: CFData<T2>,
    data3: CFData<T3>,
    data4: CFData<T4>,
): CFData<Quadruple<T1, T2, T3, T4>> {
    return combine(data1, data2, data3) { Quadruple(data1.content, data2.content, data3.content, data4.content) } as CFData<Quadruple<T1, T2, T3, T4>>
}

private fun combine(
    vararg datas: CFData<*>,
    getCombinedData: () -> Any
): CFData<*> {
    val isLoading = datas.any { it.isLoading }
    val error = datas.firstOrNull { it.error != null }?.error
    val content = getCombinedData()

    return CFData(content, isLoading, error)
}
