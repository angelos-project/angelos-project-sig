/**
 * Copyright (c) 2022 by Kristoffer Paulsson <kristoffer.paulsson@talenten.se>.
 *
 * This software is available under the terms of the MIT license. Parts are licensed
 * under different terms if stated. The legal terms are attached to the LICENSE file
 * and are made available on:
 *
 *      https://opensource.org/licenses/MIT
 *
 * SPDX-License-Identifier: MIT
 *
 * Contributors:
 *      Kristoffer Paulsson - initial implementation
 */

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.dokka")
}

group = Project.name
version = Project.version

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        val main by compilations.getting
        val processResources = main.processResourcesTaskName
        (tasks[processResources] as ProcessResources).apply {
            dependsOn(":jni-signals:assemble")
            from("${project(":jni-signals").buildDir}/lib/main/release/stripped")
        }
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
            systemProperty(
                "java.library.path",
                file("${buildDir}/processedResources/jvm/main").absolutePath
            )
        }
    }
    js(BOTH) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies{
                implementation(Libs.coro) {
                    version {
                        strictly("${Versions.coro}-native-mt")
                    }
                }
                implementation(Libs.stately)
                implementation(Libs.collections)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(TestLibs.coro)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(Libs.stately)
            }
        }
        val jsMain by getting
        val nativeMain by getting
    }
}

tasks.dokkaHtml.configure {
    outputDirectory.set(buildDir.resolve("dokka"))
    dokkaSourceSets {
        configureEach {
            //includes.from("docs/doc.md")
        }
    }
}