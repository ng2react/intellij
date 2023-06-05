package io.github.ng2react.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(name = "io.github.ng2react.settings.A2RSettingsState", storages = [Storage("SdkSettingsPlugin.xml")])

internal class A2RSettingsState : PersistentStateComponent<A2RSettingsState>    {
    var angularRoot: String? = null
    var reactRoot: String? = null
    var testRoot: String? = null

    companion object {
        fun getInstance(): A2RSettingsState? {
            return ApplicationManager.getApplication().getService(A2RSettingsState::class.java)
        }
    }
    override fun getState(): A2RSettingsState? {
        return ApplicationManager.getApplication().getService(A2RSettingsState::class.java);
    }

    override fun loadState(state: A2RSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }
}