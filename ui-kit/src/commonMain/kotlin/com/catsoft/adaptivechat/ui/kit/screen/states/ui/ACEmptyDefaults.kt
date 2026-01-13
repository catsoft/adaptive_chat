package com.catsoft.adaptivechat.ui.kit.screen.states.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.catsoft.adaptivechat.ui.kit.api.delegates.ComposableContent
import com.catsoft.adaptivechat.ui.kit.api.textClause.TextClause
import com.catsoft.adaptivechat.ui.kit.api.spacing.Spacing
import com.catsoft.adaptivechat.ui.kit.modifier.ms
import com.catsoft.adaptivechat.ui.kit.modifier.sv
import com.catsoft.adaptivechat.ui.kit.screen.layout.CenteredLayout
import com.catsoft.adaptivechat.ui.kit.text.ACText

@Composable
fun ACEmptyDefault(
    modifier: Modifier = ms,
    title: TextClause,
    description: TextClause? = null,
    icon: ComposableContent,
) {
    CenteredLayout(modifier) {
        icon()

        Spacing.small.sv

        ACText(title)

        description?.let {
            Spacing.small.sv

            ACText(it)
        }
    }
}