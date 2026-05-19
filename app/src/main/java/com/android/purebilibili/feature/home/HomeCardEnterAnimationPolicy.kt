package com.android.purebilibili.feature.home

internal fun resolveHomeCardEnterAnimationEnabledAtMount(
    baseAnimationEnabled: Boolean,
    isReturningFromDetail: Boolean,
    isSwitchingCategory: Boolean,
    isSharedTransitionEnabled: Boolean
): Boolean {
    if (!baseAnimationEnabled) return false
    if (isReturningFromDetail) return false
    if (isSwitchingCategory) return false
    if (isSharedTransitionEnabled) return false
    return true
}
