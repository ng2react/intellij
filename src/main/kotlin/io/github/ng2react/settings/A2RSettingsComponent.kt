package io.github.ng2react.settings


import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import org.jetbrains.annotations.NotNull
import javax.swing.JComponent
import javax.swing.JPanel

class A2RSettingsComponent {
    private var myMainPanel: JPanel? = null
    private val angularRoot = JBTextField()

    fun A2RSettingsComponent() {
        myMainPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Angular source root"), angularRoot, 1, true)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    fun getPanel(): JPanel? {
        return myMainPanel
    }

    fun getPreferredFocusedComponent(): JComponent {
        return angularRoot
    }

    @NotNull
    fun getAngularRootText(): String? {
        return angularRoot.text
    }

    fun setAngularRootText(@NotNull newText: String?) {
        angularRoot.text = newText
    }
}