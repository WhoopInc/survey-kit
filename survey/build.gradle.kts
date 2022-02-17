@file:Suppress("SuspiciousCollectionReassignment")

import com.quickbirdstudios.surveykit.gradePass
import com.quickbirdstudios.surveykit.gradleUser

plugins {
    id("com.android.library")
    kotlin("android")
    id("org.jetbrains.kotlin.android.extensions")
    `maven-publish`
}

androidExtensions { isExperimental = true }

afterEvaluate {
    val projectName = name

    publishing {
        repositories {
            maven {
                url = uri("https://nexus.qa.whoop.com/repository/maven-releases")
                credentials {
                    username = project.gradleUser()
                    password = project.gradePass()
                }
            }
        }
        publications {
            register<MavenPublication>("release") {
                artifact(file("$buildDir/outputs/aar/$projectName-release.aar"))
                groupId = Library.groupId
                artifactId = Library.artifactId
                version = Library.version
            }
        }
    }
}

android {
    compileSdkVersion(Project.Android.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Project.Android.minSdkVersion)
        targetSdkVersion(Project.Android.targetSdkVersion)
        testInstrumentationRunner = Project.Android.testInstrumentationRunner
    }

    testOptions {
        animationsDisabled = true
    }
}

dependencies {
    implementation(Deps.Kotlin.stdlib)
    api(Deps.Kotlin.coroutines)
    implementation(Deps.Kotlin.androidCoroutines)
    implementation(Deps.AndroidSupport.appCompat)
    implementation(Deps.AndroidSupport.constraint)
    implementation(Deps.AndroidSupport.recyclerView)
    implementation(Deps.lottie)
    implementation(Deps.PlayServices.maps)

    testImplementation(Deps.Test.jUnitJupiter)
    testImplementation(Deps.Test.jUnitPlatform)
}
