package lol.koblizek.gradle.plugin

import lol.koblizek.gradle.plugin.tasks.PrepareDependenciesTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration

class TestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        configurable = target.configurations.create("configurable")
        target.tasks.register("prepare", PrepareDependenciesTask::class.java)

        target.afterEvaluate {
            if (dependencies.isNotEmpty()) {
                configurable.dependencies.add(target.dependencies.create(dependencies[0]))
                target.configurations.getByName("implementation")
                    .extendsFrom(configurable)
            }
        }
    }
    companion object {
        lateinit var configurable: Configuration
        var dependencies: ArrayList<String> = ArrayList()
    }
}