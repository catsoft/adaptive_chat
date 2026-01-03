package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.NativePaint
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.paging.CombinedLoadStates
import app.cash.paging.LoadState
import com.catsoft.adaptivechat.ui.kit.api.delegates.ComposableContent
import com.chatfuel.common.timber
import com.chatfuel.palette.primary_20
import com.chatfuel.shared.permissions.getPermissionStateSafely
import com.chatfuel.shared.uiKit.clickableIcon.IconDto
import com.chatfuel.shared.uiKit.modifier.CFModifier
import com.chatfuel.shared_api.ui.delegates.ComposableContent
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.io.files.Path
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import kotlin.reflect.KFunction1


@Composable
fun WithLocalColor(value: Color, content: ComposableContent) {
    CompositionLocalProvider(LocalContentColor.provides(value)) {
        content()
    }
}

@Composable
fun BlockBackNavigation() = BackHandler(true) { }

fun <T> Flow<T>.catchAndLog(): Flow<T> = this.catch { timber().e("", it) }

fun LoadState.isLoaded(): Boolean = this is androidx.paging.LoadState.NotLoading && this.endOfPaginationReached

fun CombinedLoadStates.isLoaded(): Boolean = this.refresh.isLoaded() || this.append.isLoaded() || this.prepend.isLoaded()

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


//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//fun RequestIfNotGranted(permissionState: PermissionState) {
//    LaunchedEffect(permissionState.status) {
//        if (permissionState.status != PermissionStatus.Granted) {
//            permissionState.launchPermissionRequest()
//        }
//    }
//}
//
//
//@OptIn(ExperimentalPermissionsApi::class)
//class EmptyPermissionState(override val permission: String = "", override val status: PermissionStatus = PermissionStatus.Granted) : PermissionState {
//    override fun launchPermissionRequest() = Unit
//}

internal expect fun NativePaint.setMaskFilter(blurRadius: Float)

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


suspend fun List<Permission>.hasAllPermissions(context: PermissionsController): Boolean = if (this.isEmpty()) true else all { context.hasPermission(it) }

suspend fun PermissionsController.hasPermission(permission: Permission): Boolean {
    val result = getPermissionStateSafely(this, permission) == PermissionState.Granted
    return result
}

fun String.isFileLocal(): Boolean = !(this.startsWith("http://") || this.startsWith("https://")) && (this.startsWith("file:") || this.startsWith("content:") || this.startsWith("data:") || this.startsWith("/"))


fun Path.isFileLocal(): Boolean = this.toString().isFileLocal()

fun <T> SnapshotStateList<T>.swapList(list: List<T>) {
    this.clear()
    this.addAll(list)
}

@Composable
fun ImageVector.asIcon(
    modifier: Modifier = CFModifier,
    tint: Color = LocalContentColor.current,
    contentDescription: String = this.name,
) = Icon(
    imageVector = this,
    tint = tint,
    contentDescription = contentDescription,
    modifier = modifier
)

@Composable
fun ImageVector.asColoredIcon(
    modifier: Modifier = CFModifier,
    tint: Color = Color.Unspecified,
    contentDescription: String = this.name,
) = asIcon(
    modifier = modifier,
    tint = tint,
    contentDescription = contentDescription
)

@Composable
fun ImageVector.asIconDto(
    modifier: Modifier = CFModifier,
    tint: Color = LocalContentColor.current,
    contentDescription: String = this.name,
) = IconDto({ this.asIcon(modifier, tint, contentDescription) })

@Composable
fun ImageVector.asImage(
    modifier: Modifier = CFModifier,
    contentDescription: String = this.name,
    contentScale: ContentScale = ContentScale.Fit
) = Image(
    imageVector = this,
    contentDescription = contentDescription,
    modifier = modifier,
    contentScale = contentScale
)

@Composable
fun DrawableResource.asImage(
    modifier: Modifier = CFModifier,
    contentDescription: String = "",
    contentScale: ContentScale = ContentScale.Fit
) = Image(
    painter = painterResource(resource = this),
    contentDescription = contentDescription,
    modifier = modifier,
    contentScale = contentScale

)