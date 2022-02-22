@file:Suppress("UnstableApiUsage")

package com.quickbirdstudios.surveykit

import Library
import groovy.util.Node
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register

private const val gradleRepoUser = "gradle_user"
private const val gradleRepoPass = "gradle_pass"

fun Project.configureLibraryPublication() {
    configureSourcesJarTaskIfNecessary()
    configureLibraryAarPublication()
    configurePublishTask()
}

internal fun Project.configureLibraryAarPublication() {
    val projectName = name
    extensions.getByType<PublishingExtension>().publications {
        register<MavenPublication>("aar") {
            groupId = Library.groupId
            artifactId = Library.artifactId
            version = Library.version

            artifact(file("$buildDir/outputs/aar/$projectName-release.aar"))
            artifact(getSourcesJarTask())

            pom.withXml {
                asNode().appendNode("dependencies").apply {
                    configurations["implementation"].dependencies.forEach { dependency ->
                        val dependencyNode = appendNode("dependency")
                        dependencyNode.appendDependency(dependency, scope = "runtime")
                    }
                    configurations["api"].dependencies.forEach { dependency ->
                        val dependencyNode = appendNode("dependency")
                        dependencyNode.appendDependency(dependency)
                    }
                }
            }
        }
    }
}

 fun Node.appendDependency(dependency: Dependency, scope: String? = null) {
     if (scope != null) {
         appendNode("scope", scope)
     }
     appendNode("groupId", dependency.group)
     appendNode("artifactId", dependency.name)
     appendNode("version", dependency.version)
 }

internal fun Project.configurePublishTask() = afterEvaluate {
    val publish = tasks["publish"]
    val assembleRelease = tasks["assembleRelease"]
    val publishAarPublicationToMavenLocal = tasks["publishAarPublicationToMavenLocal"]
    publishAarPublicationToMavenLocal.dependsOn(assembleRelease)
    publish.dependsOn(publishAarPublicationToMavenLocal)
}

fun Project.gradleUser(): String {
    return environmentVariableOrPropertyOrStub(gradleRepoUser)
}

fun Project.gradePass(): String {
    return environmentVariableOrPropertyOrStub(gradleRepoPass)
}

private fun Project.environmentVariableOrPropertyOrStub(key: String): String {
    return System.getenv(key) ?: project.properties.getOrDefault(key, "stub").toString()
}
