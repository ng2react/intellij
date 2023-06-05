package io.github.ng2react.toolWindow

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.content.ContentFactory
import io.github.ng2react.service.Ng2rService
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

        private val ng2rService = toolWindow.project.service<Ng2rService>()

        fun getContent() = JBBox.createVerticalBox().apply {
            val root = this
            ng2rService.searchAll().forEach {
                val filename = it.key.substringAfterLast('/')
                add(JBBox.createVerticalGlue().apply {
                    add(JBLabel(filename))
                    it.value.forEach { c ->
                        add(JBBox.createHorizontalBox().apply {
                            add(JBLabel(c.name))
                            add(JButton("Convert").apply {
                                addActionListener {
                                    isEnabled = false
                                    ng2rService.convert(c.file, c).then {
                                        println(it.result.get(0).markdown)
                                    }.onError {
                                        println(it.message)
                                    }
                                }
                            })
                        })
                    }
                })
            }
        }
    }
}