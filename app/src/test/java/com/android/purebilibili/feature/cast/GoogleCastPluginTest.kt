package com.android.purebilibili.feature.cast

import com.android.purebilibili.feature.plugin.GOOGLE_CAST_PLUGIN_ID
import com.android.purebilibili.feature.plugin.GoogleCastPlugin
import com.android.purebilibili.plugin.sdk.PluginCapability
import io.github.alexzhirkevich.cupertino.icons.CupertinoIcons
import io.github.alexzhirkevich.cupertino.icons.outlined.Tv
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GoogleCastPluginTest {

    private val plugin = GoogleCastPlugin()

    @Test
    fun `plugin id is google_cast`() {
        assertEquals("google_cast", plugin.id)
        assertEquals(GOOGLE_CAST_PLUGIN_ID, plugin.id)
    }

    @Test
    fun `plugin name is Google Cast`() {
        assertEquals("Google Cast", plugin.name)
    }

    @Test
    fun `description mentions Chromecast or Google Cast`() {
        val desc = plugin.description.lowercase()
        assertTrue(
            "description should mention casting to devices",
            desc.contains("chromecast") || desc.contains("google cast") || desc.contains("投屏")
        )
    }

    @Test
    fun `version is 0 dot 1 dot 0`() {
        assertEquals("0.1.0", plugin.version)
    }

    @Test
    fun `author is BiliPai project group`() {
        assertEquals("BiliPai项目组", plugin.author)
    }

    @Test
    fun `icon is Tv outlined`() {
        assertEquals(CupertinoIcons.Outlined.Tv, plugin.icon)
    }

    @Test
    fun `capability manifest includes expected capabilities`() {
        val manifest = plugin.capabilityManifest
        assertEquals(GOOGLE_CAST_PLUGIN_ID, manifest.pluginId)
        assertEquals("Google Cast", manifest.displayName)
        assertEquals("0.1.0", manifest.version)
        assertEquals(1, manifest.apiVersion)
        assertEquals(
            "com.android.purebilibili.feature.plugin.GoogleCastPlugin",
            manifest.entryClassName
        )
        val caps = manifest.capabilities
        assertTrue("should include PLAYER_STATE", PluginCapability.PLAYER_STATE in caps)
        assertTrue("should include PLAYER_CONTROL", PluginCapability.PLAYER_CONTROL in caps)
        assertTrue("should include NETWORK", PluginCapability.NETWORK in caps)
        assertTrue("should include PLUGIN_STORAGE", PluginCapability.PLUGIN_STORAGE in caps)
    }
}
