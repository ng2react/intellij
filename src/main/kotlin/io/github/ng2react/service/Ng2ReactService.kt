package io.github.ng2react.service

import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBLabel
import io.github.ng2react.AngularJSToReact
import io.github.ng2react.Ng2ReactBundle
import io.github.ng2react.Ng2rCommonOptions
import io.github.ng2react.Ng2rComponent
import io.github.ng2react.Ng2rSearchResult
import java.util.*

@Service(Service.Level.PROJECT)
class Ng2ReactService(val project: Project) {

    init {
        thisLogger().info(Ng2ReactBundle.message("projectService", project.name))
    }


    fun getRandomNumber() = (1..100).random()

    fun getFilename() = this.project.projectFile?.name

    fun search(file: String): List<Ng2rComponent> {
        val opt = Ng2rCommonOptions(projectRoot(), file)
        thisLogger().warn(opt.toString())
        return AngularJSToReact().search(opt).result;
    }

    fun searchAll(): List<Ng2rComponent> {
        return getAllAngularFiles().flatMap { file ->
            search(file).map { component ->
                component
            }
        }
    }

    private fun getAllAngularFiles(): List<String> {
        return Collections.emptyList()
    }
    private fun projectRoot(): String {
        return project.basePath!!
    }

}