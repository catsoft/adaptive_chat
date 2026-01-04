package com.catsoft.adaptivechat.ui.kit.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.catsoft.adaptivechat.ui.kit.modifier.h
import com.catsoft.adaptivechat.ui.kit.modifier.m
import com.catsoft.adaptivechat.ui.kit.modifier.mw
import com.catsoft.adaptivechat.ui.kit.modifier.s
import com.catsoft.adaptivechat.ui.kit.modifier.sh
import com.catsoft.adaptivechat.ui.kit.modifier.sv
import com.valentinilk.shimmer.shimmer

@Composable
fun TitleListItemShimmer(
    withSubtext: Boolean = false,
    withLeadingIcon: Boolean = false,
    withTrailingIcon: Boolean = false,
    modifier: Modifier = m
) {
    Box(
        modifier = modifier
            .shimmer()
            .h(if (withSubtext) 56 else 48)
            .mw(),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(modifier = (if (withSubtext) 56 else 48).h, contentAlignment = Alignment.CenterStart) {
            Row(
                m.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (withLeadingIcon) {
                    Box(56.s, contentAlignment = Alignment.Center) {
                        ShimmerBox(size = 24, corners = 12)
                    }
                } else {
                    16.sh
                }

                Column(modifier = m.weight(1f)) {
                    ShimmerBoxHorizontal(
                        height = 18,
                        corners = 10,
                        modifier = m.fillMaxWidth(0.3F)
                    )
                    
                    if (withSubtext) {
                        2.sv
                        ShimmerBoxHorizontal(
                            height = 14,
                            corners = 9,
                            modifier = m.fillMaxWidth(0.5F)
                        )
                    }
                }

                if (withTrailingIcon) {
                    Box(56.s, contentAlignment = Alignment.Center) {
                        ShimmerBox(size = 24, corners = 12)
                    }
                }
            }
        }
    }
}

@Composable
fun ListItemShimmer(
    withSubtitle: Boolean = false,
    withLeadingIcon: Boolean = false,
    withTrailingIcon: Boolean = false,
    modifier: Modifier = m
) {
    Row(
        modifier
            .shimmer()
            .defaultMinSize(minHeight = 56.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (withLeadingIcon) {
            Box(56.s, contentAlignment = Alignment.Center) {
                ShimmerBox(size = 24, corners = 12)
            }
        } else {
            16.sh
        }

        Column(
            m
                .weight(1f)
                .padding(top = 8.dp, end = 16.dp, bottom = 8.dp)
        ) {
            ShimmerBoxHorizontal(
                height = 18,
                corners = 10,
                modifier = m.fillMaxWidth(0.4F)
            )
            
            if (withSubtitle) {
                6.sv
                Row {
                    // Subtitle shimmer
                    ShimmerBoxHorizontal(
                        height = 14,
                        corners = 9,
                        modifier = m.fillMaxWidth(0.55F)
                    )
                }
            }
        }

        if (withTrailingIcon) {
            Box(56.s, contentAlignment = Alignment.Center) {
                ShimmerBox(size = 24, corners = 12)
            }
        }
    }
}