package com.android.purebilibili.feature.home

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HomeCardEnterAnimationPolicyTest {

    @Test
    fun baseDisabled_keepsEnterAnimationDisabled() {
        assertFalse(
            resolveHomeCardEnterAnimationEnabledAtMount(
                baseAnimationEnabled = false,
                isReturningFromDetail = false,
                isSwitchingCategory = false,
                isSharedTransitionEnabled = false
            )
        )
    }

    @Test
    fun returningFromDetail_disablesEnterAnimationAtMount() {
        assertFalse(
            resolveHomeCardEnterAnimationEnabledAtMount(
                baseAnimationEnabled = true,
                isReturningFromDetail = true,
                isSwitchingCategory = false,
                isSharedTransitionEnabled = false
            )
        )
    }

    @Test
    fun switchingCategory_disablesEnterAnimationAtMount() {
        assertFalse(
            resolveHomeCardEnterAnimationEnabledAtMount(
                baseAnimationEnabled = true,
                isReturningFromDetail = false,
                isSwitchingCategory = true,
                isSharedTransitionEnabled = false
            )
        )
    }

    @Test
    fun sharedTransitionEnabled_disablesEnterAnimationAtMount() {
        assertFalse(
            resolveHomeCardEnterAnimationEnabledAtMount(
                baseAnimationEnabled = true,
                isReturningFromDetail = false,
                isSwitchingCategory = false,
                isSharedTransitionEnabled = true
            )
        )
    }

    @Test
    fun normalHomeMountWithoutSharedTransition_enablesEnterAnimation() {
        assertTrue(
            resolveHomeCardEnterAnimationEnabledAtMount(
                baseAnimationEnabled = true,
                isReturningFromDetail = false,
                isSwitchingCategory = false,
                isSharedTransitionEnabled = false
            )
        )
    }
}
