package io.github.ng2react.toolWindow

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import io.github.ng2react.Ng2ReactBundle
import io.github.ng2react.service.Ng2ReactService
import java.util.*
import javax.swing.JButton

class A2RToolWindowFactory : ToolWindowFactory {

    private val contentFactory = ContentFactory.getInstance()

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val ATRToolWindow = ATRToolWindow(toolWindow)
        val content = contentFactory.createContent(ATRToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class ATRToolWindow(toolWindow: ToolWindow) {

        private val service = toolWindow.project.service<Ng2ReactService>()

        fun getContent() = JBPanel<JBPanel<*>>().apply {
            val label = JBLabel(Ng2ReactBundle.message("randomLabel", "?"))

            add(label)
            add(JButton(Ng2ReactBundle.message("shuffle")).apply {
                addActionListener {
                    label.text = Ng2ReactBundle.message("randomLabel", service.getRandomNumber())
                }
            })

            add(JBLabel(Ng2ReactBundle.message("current_file.name", service.getFilename() ?: "No file")))

            add(JBLabel(Ng2ReactBundle.message("current_file.components.label")))

            service.searchAll().forEach { component ->
                add(JBLabel(component.name))
            }
        }
    }
}