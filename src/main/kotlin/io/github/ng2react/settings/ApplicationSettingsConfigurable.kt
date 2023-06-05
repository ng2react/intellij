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

    override fun getPreferredFocusedComponent(): JComponent? {
        return a2RSettingsComponent!!.getPreferredFocusedComponent()
    }

    override fun createComponent(): JComponent? {
        a2RSettingsComponent = A2RSettingsComponent()
        return a2RSettingsComponent!!.getPanel()
    }

    override fun isModified(): Boolean {
        val settings = getInstance()!!
        val modified = a2RSettingsComponent!!.getAngularRootText() != settings.angularRoot
        return modified
    }

    override fun apply() {
        val settings = getInstance()!!
        settings.angularRoot = a2RSettingsComponent!!.getAngularRootText()!!
    }

    override fun reset() {
        val settings = getInstance()!!
        a2RSettingsComponent!!.setAngularRootText(settings.angularRoot)
    }

    override fun disposeUIResources() {
        a2RSettingsComponent = null
    }
}
