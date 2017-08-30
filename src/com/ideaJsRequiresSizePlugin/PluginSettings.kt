package com.ideaJsRequiresSizePlugin

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.StoragePathMacros
import com.intellij.util.xmlb.XmlSerializerUtil

@State(name = "JsRequiresSizeSettings")
@Storage(StoragePathMacros.WORKSPACE_FILE)
class PluginSettings : PersistentStateComponent<PluginSettings> {
    var maxFileSize = 1024L * 1024L // 1 MB by default

    override fun getState(): PluginSettings? = this
    override fun loadState(state: PluginSettings) = XmlSerializerUtil.copyBean(state, this)
}
