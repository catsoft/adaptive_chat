package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.graphics.Color
import com.catsoft.adaptivechat.ui.kit.api.delegates.ComposableContent
import kotlinx.coroutines.Job
import kotlin.reflect.KFunction1


@Composable
fun WithLocalColor(value: Color, content: ComposableContent) {
    CompositionLocalProvider(LocalContentColor.provides(value)) {
        content()
    }
}

@Composable
fun BlockBackNavigation() = BackHandler(true) { }

@Composable
fun SubscribeOnBoolChange(
    changesState: MutableState<Boolean>,
    initialState: Boolean,
    action: KFunction1<Boolean, Job>,
) {
    LaunchedEffect(changesState.value) {
        if (changesState.value != initialState) {
            action(changesState.value)
        }
    }
}

fun String.toColorInt(): Int {
    var color = this.removePrefix("#")
    if (color.length == 6) {
        color = "FF$color"
    }
    return color.toLong(16).toInt()
}

fun Color.getColorString(): String {
    val r = (red * 255).toInt()
    val g = (green * 255).toInt()
    val b = (blue * 255).toInt()
    return "#" + r.toHex() + g.toHex() + b.toHex()
}

private fun Int.toHex(): String {
    val hex = this.toString(16)
    return if (hex.length == 1) "0$hex" else hex
}

fun <T> SnapshotStateList<T>.swapList(list: List<T>) {
    this.clear()
    this.addAll(list)
}