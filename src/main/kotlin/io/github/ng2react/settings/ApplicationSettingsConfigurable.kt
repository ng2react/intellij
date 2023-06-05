package io.github.ng2react.settings

import com.intellij.openapi.options.Configurable
import io.github.ng2react.settings.A2RSettingsState.Companion.getInstance
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

class ApplicationSettingsConfigurable : Configurable {
    private var a2RSettingsComponent: A2RSettingsComponent? = null

    // A default constructor with no arguments is required because this implementation
    // is registered as an applicationConfigurable EP
    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String? {
        return "AngularJS to React"
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return a2RSettingsComponent!!.getPreferredFocusedComponent()
    }

    override fun createComponent(): JComponent? {
        a2RSettingsComponent = A2RSettingsComponent()
        return a2RSettingsComponent!!.getPanel()
    }

    override fun isModified(): Boolean {
        val settings = getInstance()!!
        if (a2RSettingsComponent!!.getAngularRootText() != settings.angularRoot) return true
        if (a2RSettingsComponent!!.getApiKeyText() != settings.apiKey) return true
        if (a2RSettingsComponent!!.getTestRootText() != settings.testRoot) return true
        if (a2RSettingsComponent!!.getReactRootText() != settings.reactRoot) return true
        if (a2RSettingsComponent!!.getModelText() != settings.model) return true
        if (a2RSettingsComponent!!.getTemperatureValue() != settings.temperature) return true
        if (a2RSettingsComponent!!.getOrganizationText() != settings.organization) return true
        if (a2RSettingsComponent!!.getTargetLanguageText() != settings.targetLanguage) return true
        return false
    }

    override fun apply() {
        val settings = getInstance()!!
        val ui = a2RSettingsComponent!!
        settings.angularRoot = ui.getAngularRootText()
        settings.apiKey = ui.getApiKeyText()
        settings.testRoot = ui.getTestRootText()
        settings.reactRoot = ui.getReactRootText()
        settings.model = ui.getModelText() ?: settings.model
        settings.temperature = ui.getTemperatureValue() ?: settings.temperature
        settings.organization = ui.getOrganizationText()
        settings.targetLanguage = ui.getTargetLanguageText()
    }

    override fun reset() {
        val settings = getInstance()!!
        a2RSettingsComponent!!.setAngularRootText(settings.angularRoot)
        a2RSettingsComponent!!.setApiKeyText(settings.apiKey)
        a2RSettingsComponent!!.setTestRootText(settings.testRoot)
        a2RSettingsComponent!!.setReactRootText(settings.reactRoot)
        a2RSettingsComponent!!.setModelText(settings.model)
        a2RSettingsComponent!!.setTemperatureValue(settings.temperature)
        a2RSettingsComponent!!.setOrganizationText(settings.organization)
        a2RSettingsComponent!!.setTargetLanguageText(settings.targetLanguage)
    }

    override fun disposeUIResources() {
        a2RSettingsComponent = null
    }
}
