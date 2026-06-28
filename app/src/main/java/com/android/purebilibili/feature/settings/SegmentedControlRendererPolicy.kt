package com.android.purebilibili.feature.settings

import androidx.compose.ui.graphics.Color
import com.android.purebilibili.core.theme.AndroidNativeVariant

internal enum class Md3SegmentedControlRenderer {
    MATERIAL_SEGMENTED_BUTTONS,
    MIUIX_TAB_ROW,
}

internal fun resolveMd3SegmentedControlRenderer(
    androidNativeVariant: AndroidNativeVariant
): Md3SegmentedControlRenderer = when (androidNativeVariant) {
    AndroidNativeVariant.MIUIX -> Md3SegmentedControlRenderer.MIUIX_TAB_ROW
    AndroidNativeVariant.MATERIAL3 -> Md3SegmentedControlRenderer.MATERIAL_SEGMENTED_BUTTONS
}

internal data class MiuixSegmentedTabRowColors(
    val backgroundColor: Color,
    val contentColor: Color,
    val selectedBackgroundColor: Color,
    val selectedContentColor: Color,
)

internal fun resolveMiuixSegmentedTabRowColors(
    tokens: Md3SegmentedControlColorTokens
): MiuixSegmentedTabRowColors = MiuixSegmentedTabRowColors(
    backgroundColor = Color.Transparent,
    contentColor = tokens.inactiveContentColor,
    selectedBackgroundColor = tokens.activeContainerColor,
    selectedContentColor = tokens.activeContentColor,
)