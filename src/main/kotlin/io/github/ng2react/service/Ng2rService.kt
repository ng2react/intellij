package io.github.ng2react.service

import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import io.github.ng2react.*
import io.github.ng2react.settings.A2RSettingsState
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

@Service(Service.Level.PROJECT)
class Ng2rService(val project: Project) {
    private val cli by lazy { AngularJSToReact() }

    init {
        thisLogger().info(Ng2ReactBundle.message("projectService", project.name))
        println(cli.help())
    }

    fun convert(file: String, component: Ng2rComponent): Ng2rGenerationResponse {
        val opt: Ng2rConvertOptions
        A2RSettingsState.getInstance()!!.apply {
            opt = Ng2rConvertOptions()
                .withComponentName(component.name)
                .withFile(file)
                .withCwd(project.basePath!!)
                .withSourceRoot(angularRoot)
                .withApiKey(apiKey)
                .withModel(model)
                .withTemperature(temperature)
                .withTargetLanguage(targetLanguage)
                .withCustomPrompt(null) // TODO: configure custom prompt
        }
        return cli.convert(opt)
    }

    fun search(file: String): List<Ng2rComponent> {
        val opt = Ng2rCommonOptions(projectRoot(), file)
        return cli.search(opt).result;
    }

    fun searchAll(): Map<String, List<Ng2rComponent>> {
        val settings = A2RSettingsState.getInstance()!!
        val root = Paths.get(project.basePath!!).toFile();
        return findAllAngularFiles(Path.of(project.basePath!!, settings.angularRoot))
            .flatMap {
                search(it.relativeTo(root).path).map { component ->
                    component
                }
            }.groupBy { it.file }
    }

    private fun projectRoot(): String {
        return project.basePath!!
    }

}