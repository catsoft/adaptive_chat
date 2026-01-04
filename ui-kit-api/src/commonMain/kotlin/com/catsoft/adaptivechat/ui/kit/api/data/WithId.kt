package com.catsoft.adaptivechat.ui.kit.api.data

interface WithId {
    val id: String
}

fun <T : WithId> List<T>.copyList(id: String, update: T.() -> T): List<T> {
    return this.map { if (it.id == id) update(it) else it }
}

fun <T> List<T>.updateLast(update: T.() -> T): List<T> = this.mapIndexed { index, item ->
    if (index == this.size - 1) {
        update(item)
    } else {
        item
    }
}

fun <T> List<T>.updateFirst(update: T.() -> T): List<T> = this.mapIndexed { index, item ->
    if (index == 0) {
        update(item)
    } else {
        item
    }
}