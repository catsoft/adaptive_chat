package com.catsoft.adaptivechat.ui.kit.screen.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.catsoft.adaptivechat.ui.kit.api.delegates.ColumnScopeComposable
import com.catsoft.adaptivechat.ui.kit.api.theme.ScreenSpacing
import com.catsoft.adaptivechat.ui.kit.modifier.alC
import com.catsoft.adaptivechat.ui.kit.modifier.m
import com.catsoft.adaptivechat.ui.kit.modifier.ms
import com.catsoft.adaptivechat.ui.kit.modifier.ph

@Composable
fun CenteredLayout(modifier: Modifier = ms, content: ColumnScopeComposable) {
    Box(
        modifier.ms(),
        contentAlignment = alC
    ) {
        Column(m.ph(ScreenSpacing.horizontalPadding)) {
            content()
        }
    }
}