import org.gradle.api.Plugin
import org.gradle.api.Project


class MainGradlePlugin:Plugin<Project> {
    override fun apply(project: Project) {
        project.repositories.apply {
            google()
            mavenCentral()
        }

        project.plugins.apply{
           apply("kotlin-jvm")
        }
    }
//    repositories {
//        google()
//        mavenCentral()
//    }
//    plugins {
//        kotlin("jvm") version "1.9.24"
//    }
    private fun applyFun(project: Project) {

    }
}