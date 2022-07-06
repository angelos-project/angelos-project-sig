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

group = MetaProject.group
version = MetaProject.version

plugins {
    id("org.jetbrains.kotlinx.kover") version MetaProject.koverVersion
    id("com.github.nbaztec.coveralls-jacoco") version MetaProject.coverallsVersion
    id("project-publish-setup")
}

allprojects {
    repositories {
        mavenCentral()
    }
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(MetaProject.kotlinLibrary)
        classpath(MetaProject.dokkaLibrary)
    }
}

coverallsJacoco {
    reportPath = "$projectDir/build/reports/kover/report.xml"

    reportSourceSets = listOf(
        File("$projectDir/signals/src/commonMain/kotlin/"),
        File("$projectDir/signals/src/jvmMain/kotlin/"),
        File("$projectDir/signals/src/jsMain/kotlin/"),
        File("$projectDir/signals/src/nativeMain/kotlin/"),
    )
}