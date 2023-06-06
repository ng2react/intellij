package io.github.ng2react.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import io.github.ng2react.Ng2rGptOptions

@State(name = "io.github.ng2react.settings.A2RSettingsState", storages = [Storage("SdkSettingsPlugin.xml")])
internal class A2RSettingsState : PersistentStateComponent<A2RSettingsState> {
    var angularRoot: String? = "src/angular"
    var reactRoot: String? = "src/react"
    var testRoot: String? = "src/test"
    var apiKey: String? = null
    var model = Ng2rGptOptions.Model.GPT_4
    var temperature = 0.2f
    var organization: String? = null
    var targetLanguage: Ng2rGptOptions.TargetLanguage? = null

    companion object {
        fun getInstance(): A2RSettingsState? {
            return ApplicationManager.getApplication().getService(A2RSettingsState::class.java)
        }
    }

    override fun getState(): A2RSettingsState? {
        return ApplicationManager.getApplication().getService(A2RSettingsState::class.java)
    }

    override fun loadState(state: A2RSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }
}