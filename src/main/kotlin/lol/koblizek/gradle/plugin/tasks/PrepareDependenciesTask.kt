package lol.koblizek.gradle.plugin.tasks

import lol.koblizek.gradle.plugin.TestPlugin
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class PrepareDependenciesTask : DefaultTask() {
    init {
        group = "testing"
    }

    @TaskAction
    fun task() {
        TestPlugin.dependencies.clear()
        TestPlugin.dependencies.add("com.google.guava:guava:32.1.1-jre")
    }
}