@file:Suppress("SuspiciousCollectionReassignment")

plugins {
    id("com.android.library")
    kotlin("android")
    id("org.jetbrains.kotlin.android.extensions")
//    id("com.jfrog.bintray")
    `maven-publish`
    maven
    signing
}

group = "com.whoop"
version = "2.0.8.7"

androidExtensions { isExperimental = true }

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

//project.configureLibraryPublication()

//publishing {
//    publications {
//        create<MavenPublication>("mavenJava") {
//            artifactId = "survey-kit"
//            from(components["java"])
//            versionMapping {
//                usage("java-api") {
//                    fromResolutionOf("runtimeClasspath")
//                }
//                usage("java-runtime") {
//                    fromResolutionResult()
//                }
//            }
//            pom {
//                name.set("My Library")
//                description.set("A concise description of my library")
//                url.set("http://www.example.com/library")
//                properties.set(
//                    mapOf(
//                        "myProp" to "value",
//                        "prop.with.dots" to "anotherValue"
//                    )
//                )
//                licenses {
//                    license {
//                        name.set("The Apache License, Version 2.0")
//                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
//                    }
//                }
//                developers {
//                    developer {
//                        id.set("johnd")
//                        name.set("John Doe")
//                        email.set("john.doe@example.com")
//                    }
//                }
//                scm {
//                    connection.set("scm:git:git://example.com/my-library.git")
//                    developerConnection.set("scm:git:ssh://example.com/my-library.git")
//                    url.set("http://example.com/my-library/")
//                }
//            }
//        }
//    }
//    repositories {
//        maven {
//            // change URLs to point to your repos, e.g. http://my.org/repo
//            val releasesRepoUrl = uri(layout.buildDirectory.dir("repos/releases"))
//            val snapshotsRepoUrl = uri(layout.buildDirectory.dir("repos/snapshots"))
//            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
//        }
//    }
//}

//signing {
//    sign(publishing.publications["mavenJava"])
//}

