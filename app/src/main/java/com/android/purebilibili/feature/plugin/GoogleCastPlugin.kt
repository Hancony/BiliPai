package com.android.purebilibili.feature.plugin

import androidx.compose.ui.graphics.vector.ImageVector
import com.android.purebilibili.core.plugin.Plugin
import com.android.purebilibili.core.plugin.PluginCapability
import com.android.purebilibili.core.plugin.PluginCapabilityManifest
import com.android.purebilibili.core.util.Logger
import io.github.alexzhirkevich.cupertino.icons.CupertinoIcons
import io.github.alexzhirkevich.cupertino.icons.outlined.Tv

const val GOOGLE_CAST_PLUGIN_ID = "google_cast"
private const val TAG = "GoogleCastPlugin"

class GoogleCastPlugin : Plugin {

    override val id = GOOGLE_CAST_PLUGIN_ID
    override val name = "Google Cast"
    override val description = "将视频投屏到 Chromecast / Google Cast 设备"
    override val version = "0.1.0"
    override val author = "BiliPai项目组"
    override val icon: ImageVector = CupertinoIcons.Outlined.Tv
    override val capabilityManifest: PluginCapabilityManifest = PluginCapabilityManifest(
        pluginId = id,
        displayName = name,
        version = version,
        apiVersion = 1,
        entryClassName = "com.android.purebilibili.feature.plugin.GoogleCastPlugin",
        capabilities = setOf(
            PluginCapability.PLAYER_STATE,
            PluginCapability.PLAYER_CONTROL,
            PluginCapability.NETWORK,
            PluginCapability.PLUGIN_STORAGE
        )
    )

    override suspend fun onEnable() {
        Logger.d(TAG, "Google Cast plugin enabled")
    }

    override suspend fun onDisable() {
        Logger.d(TAG, "Google Cast plugin disabled")
    }
}
