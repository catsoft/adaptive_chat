package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.catsoft.adaptivechat.logger.logger
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSNotificationCenter
import platform.UIKit.UIKeyboardDidHideNotification
import platform.UIKit.UIKeyboardDidShowNotification
import platform.UIKit.UIKeyboardWillHideNotification
import platform.UIKit.UIKeyboardWillShowNotification


@Composable
actual fun isKeyboardVisiblePlatform(): State<Boolean> {
    var keyboardVisible by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        logger().i("🔥 iOS-Keyboard: Setting up native iOS keyboard notifications")

        val notificationCenter = NSNotificationCenter.defaultCenter

        val keyboardWillShowObserver = notificationCenter.addObserverForName(
            name = UIKeyboardWillShowNotification,
            `object` = null,
            queue = null
        ) { notification ->
            logger().i("🔥 iOS-Keyboard: UIKeyboardWillShowNotification received!")
            keyboardVisible = true
        }

        val keyboardDidShowObserver = notificationCenter.addObserverForName(
            name = UIKeyboardDidShowNotification,
            `object` = null,
            queue = null
        ) { notification ->
            logger().i("🔥 iOS-Keyboard: UIKeyboardDidShowNotification received!")
            keyboardVisible = true
        }

        val keyboardWillHideObserver = notificationCenter.addObserverForName(
            name = UIKeyboardWillHideNotification,
            `object` = null,
            queue = null
        ) { notification ->
            logger().i("🔥 iOS-Keyboard: UIKeyboardWillHideNotification received!")
            keyboardVisible = false
        }

        val keyboardDidHideObserver = notificationCenter.addObserverForName(
            name = UIKeyboardDidHideNotification,
            `object` = null,
            queue = null
        ) { notification ->
            logger().i("🔥 iOS-Keyboard: UIKeyboardDidHideNotification received!")
            keyboardVisible = false
        }

        logger().i("🔥 iOS-Keyboard: All keyboard observers registered successfully!")

        onDispose {
            logger().i("🔥 iOS-Keyboard: Removing keyboard observers")
            notificationCenter.removeObserver(keyboardWillShowObserver)
            notificationCenter.removeObserver(keyboardDidShowObserver)
            notificationCenter.removeObserver(keyboardWillHideObserver)
            notificationCenter.removeObserver(keyboardDidHideObserver)
        }
    }

    return remember(keyboardVisible) {
        object : State<Boolean> {
            override val value: Boolean get() {
                logger().i("🔥 iOS-Keyboard: State.value requested, returning $keyboardVisible")
                return keyboardVisible
            }
        }
    }
}