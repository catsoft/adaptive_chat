package com.catsoft.adaptivechat.util

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import com.catsoft.adaptivechat.ui.kit.theme.AdaptiveChatTheme
import kotlin.jvm.JvmSuppressWildcards
import kotlin.reflect.KType

/**
 * Навигационные анимации:
 *
 * enterTransition - вызывается когда ЭТОТ экран открывается (переход вперед)
 * exitTransition - вызывается когда с ЭТОГО экрана уходят на следующий (переход вперед)
 * popEnterTransition - вызывается когда на ЭТОТ экран возвращаются назад (по умолчанию = enterTransition)
 * popExitTransition - вызывается когда ЭТОТ экран закрывается (переход назад, по умолчанию = exitTransition)
 *
 * Пример:
 * Screen A -> Screen B:
 * - A.exitTransition выполняется для экрана A
 * - B.enterTransition выполняется для экрана B
 *
 * Screen B -> (back) -> Screen A:
 * - B.popExitTransition выполняется для экрана B
 * - A.popEnterTransition выполняется для экрана A
 */
inline fun <reified T : Any> NavGraphBuilder.composableTheme(
    isDarkTheme: Boolean = false,
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?)? =
        null,
    noinline exitTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?)? =
        null,
    noinline popEnterTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?)? =
        enterTransition,
    noinline popExitTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?)? =
        exitTransition,
    noinline sizeTransform:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    SizeTransform?)? =
        null,
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        T::class,
        typeMap,
        deepLinks,
        enterTransition,
        exitTransition,
        popEnterTransition,
        popExitTransition,
        sizeTransform,
        content = {
            AdaptiveChatTheme(isDarkTheme) {
                content(it)
            }
        }
    )
}