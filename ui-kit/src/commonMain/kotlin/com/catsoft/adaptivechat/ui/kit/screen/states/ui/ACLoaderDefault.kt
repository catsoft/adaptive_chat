package com.catsoft.adaptivechat.ui.kit.screen.states.ui

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.catsoft.adaptivechat.ui.kit.api.spacing.Size
import com.catsoft.adaptivechat.ui.kit.modifier.m
import com.catsoft.adaptivechat.ui.kit.modifier.s
import com.catsoft.adaptivechat.ui.kit.screen.layout.CenteredLayout

@Composable
fun ACLoaderDefault(modifier: Modifier = m) {
    CenteredLayout(modifier) {
        CircularProgressIndicator(Size.progressBar.s)
    }
}