package io.github.ng2react.settings


import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import io.github.ng2react.Ng2rTestGenerationOptions
import org.jetbrains.annotations.NotNull
import javax.swing.JComponent
import javax.swing.JPanel

class A2RSettingsComponent {
    private var myMainPanel: JPanel? = null
    private val angularRoot = JBTextField()
    private val reactRoot = JBTextField()
    private val apiKey = JBTextField()
    private val testRoot = JBTextField()
    private val model = JBTextField()
    private val temperature = JBTextField()
    private val organization = JBTextField()
    private val targetLanguage = JBTextField()

    init {
        myMainPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("OpenAI API Key"), apiKey, 1, true)
            .addComponentFillVertically(JPanel(), 0)
            .addLabeledComponent(JBLabel("Angular source root"), angularRoot, 1, true)
            .addComponentFillVertically(JPanel(), 0)
            .addLabeledComponent(JBLabel("React source root"), reactRoot, 1, true)
            .addComponentFillVertically(JPanel(), 0)
            .addLabeledComponent(JBLabel("Test source root"), testRoot, 1, true)
            .addComponentFillVertically(JPanel(), 0)
            .addLabeledComponent(JBLabel("Model"), model, 1, true)
            .addComponentFillVertically(JPanel(), 0)
            .addLabeledComponent(JBLabel("Temperature"), temperature, 1, true)
            .addComponentFillVertically(JPanel(), 0)
            .addLabeledComponent(JBLabel("Organization"), organization, 1, true)
            .addComponentFillVertically(JPanel(), 0)
            .addLabeledComponent(JBLabel("Target Language"), targetLanguage, 1, true)
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

    @NotNull
    fun getApiKeyText(): String? {
        return apiKey.text
    }

    fun setApiKeyText(@NotNull newText: String?) {
        apiKey.text = newText
    }

    @NotNull
    fun getReactRootText(): String? {
        return reactRoot.text
    }

    fun setReactRootText(@NotNull newText: String?) {
        reactRoot.text = newText
    }

    @NotNull
    fun getTestRootText(): String? {
        return testRoot.text
    }

    fun setTestRootText(@NotNull newText: String?) {
        testRoot.text = newText
    }

    @NotNull
    fun getModelText(): Ng2rTestGenerationOptions.Model? {
        if (model.text.isBlank()) {
            return null
        }
        return try {
            Ng2rTestGenerationOptions.Model.fromValue(model.text.lowercase())
        } catch (e: IllegalArgumentException) {
            null
        }
    }


    fun setModelText(@NotNull newText: Ng2rTestGenerationOptions.Model?) {
        model.text = newText?.value()
    }

    @NotNull
    fun getTemperatureValue(): Float? {
        if (temperature.text.isBlank()) {
            return null
        }
        return try {
            temperature.text.toFloat()
        } catch (e: NumberFormatException) {
            null
        }
    }

    fun setTemperatureValue(@NotNull newText: Float?) {
        temperature.text = newText.toString()
    }

    @NotNull
    fun getOrganizationText(): String? {
        return organization.text
    }

    fun setOrganizationText(@NotNull newText: String?) {
        organization.text = newText
    }

    @NotNull
    fun getTargetLanguageText(): Ng2rTestGenerationOptions.TargetLanguage? {
        if (targetLanguage.text.isBlank()) {
            return null
        }
        return try {
            Ng2rTestGenerationOptions.TargetLanguage.fromValue(targetLanguage.text.lowercase())
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    fun setTargetLanguageText(@NotNull newText: Ng2rTestGenerationOptions.TargetLanguage?) {
        targetLanguage.text = newText?.value()
    }
}