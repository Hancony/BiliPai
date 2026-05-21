package com.android.purebilibili.navigation3

import androidx.compose.ui.geometry.Rect
import androidx.navigationevent.NavigationEventTransitionState
import androidx.navigationevent.NavigationEventTransitionState.InProgress
import com.android.purebilibili.core.store.PredictiveBackAnimationStyle

internal data class BiliPaiPredictiveBackGestureState(
    val active: Boolean = false,
    val progress: Float = 0f
)

internal fun resolveBiliPaiPredictiveBackGestureState(
    transitionState: NavigationEventTransitionState?
): BiliPaiPredictiveBackGestureState {
    val inProgress = transitionState as? InProgress ?: return BiliPaiPredictiveBackGestureState()
    return BiliPaiPredictiveBackGestureState(
        active = true,
        progress = inProgress.latestEvent.progress.coerceIn(0f, 1f)
    )
}

internal fun shouldEnableVideoPredictiveReturnToCard(
    currentKey: BiliPaiNavKey?,
    previousKey: BiliPaiNavKey?,
    predictiveBackAnimationStyle: PredictiveBackAnimationStyle,
    cardTransitionEnabled: Boolean,
    sourceMetadata: BiliPaiNavSourceMetadata,
    sourceBounds: Rect?
): Boolean {
    // 共享元素开启时由 sharedBounds 接管；关闭时由方向 fallback 接管。
    // 不再启用详情壳卡片回收 transform，避免与两条返回路径叠加导致闪屏。
    return false
}
