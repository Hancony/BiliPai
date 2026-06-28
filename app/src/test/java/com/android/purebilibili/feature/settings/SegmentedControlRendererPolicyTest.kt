package com.android.purebilibili.feature.settings

import androidx.compose.ui.graphics.Color
import com.android.purebilibili.core.theme.AndroidNativeVariant
import org.junit.Assert.assertEquals
import org.junit.Test

class SegmentedControlRendererPolicyTest {

    @Test
    fun `miuix variant routes md3 segmented chrome to tab row`() {
        assertEquals(
            Md3SegmentedControlRenderer.MIUIX_TAB_ROW,
            resolveMd3SegmentedControlRenderer(AndroidNativeVariant.MIUIX)
        )
    }

    @Test
    fun `material3 variant keeps segmented buttons renderer`() {
        assertEquals(
            Md3SegmentedControlRenderer.MATERIAL_SEGMENTED_BUTTONS,
            resolveMd3SegmentedControlRenderer(AndroidNativeVariant.MATERIAL3)
        )
    }

    @Test
    fun `miuix segmented tab row colors map secondary container roles`() {
        val tokens = Md3SegmentedControlColorTokens(
            outerContainerColor = Color(0xFF302322),
            activeContainerColor = Color(0xFF7A4828),
            activeContentColor = Color(0xFFFFE0D1),
            inactiveContentColor = Color(0xFFEAD0CD)
        )
        val colors = resolveMiuixSegmentedTabRowColors(tokens)

        assertEquals(Color.Transparent, colors.backgroundColor)
        assertEquals(Color(0xFF7A4828), colors.selectedBackgroundColor)
        assertEquals(Color(0xFFFFE0D1), colors.selectedContentColor)
        assertEquals(Color(0xFFEAD0CD), colors.contentColor)
    }
}