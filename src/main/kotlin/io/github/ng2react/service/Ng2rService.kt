package io.github.ng2react.service

import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import io.github.ng2react.*
import io.github.ng2react.settings.A2RSettingsState
import org.jetbrains.concurrency.AsyncPromise
import org.jetbrains.concurrency.Promise
import java.nio.file.Path
import java.util.*

@Service(Service.Level.PROJECT)
class Ng2rService(val project: Project) {
    private val LOGGER = Logger.getInstance(Ng2rService::class.java)
    private val cli by lazy { AngularJSToReact() }

    init {
        thisLogger().info(Ng2ReactBundle.message("projectService", project.name))
        println(cli.help())
    }

    fun convert(file: String, component: Ng2rComponent): Promise<Ng2rGenerationResponse> {
        val opt: Ng2rConvertOptions
        A2RSettingsState.getInstance()!!.apply {
            opt = Ng2rConvertOptions()
                .withComponentName(component.name)
                .withFile(file)
                .withSourceRoot(Path.of(project.basePath!!, angularRoot).toString())
                .withApiKey(apiKey)
                .withModel(model)
                .withTemperature(temperature)
                .withTargetLanguage(targetLanguage)
                .withCustomPrompt(null) // TODO: configure custom prompt
        }
        return AsyncPromise<Ng2rGenerationResponse>().apply {
            LOGGER.debug("convert: $opt")
            println("convert: $opt async")
            setResult(cli.convert(opt))
        }
    }

    fun search(file: String): List<Ng2rComponent> {
        val opt = Ng2rSearchOptions(file)
        return cli.search(opt).result
    }

    fun searchAll(): Map<String, List<Ng2rComponent>> {
        val settings = A2RSettingsState.getInstance()!!
        return findAllAngularFiles(Path.of(project.basePath!!, settings.angularRoot))
            .flatMap {
                search(it.path).map { component ->
                    component
                }
            }.groupBy { it.file }
    }

    fun checkConnection(): Boolean {
        val apiKey = A2RSettingsState.getInstance()!!.apiKey
        return cli.checkConnection(Ng2rCheckOptions().withApiKey(apiKey))
    }
}