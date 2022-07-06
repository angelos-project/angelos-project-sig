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

object MetaProject {
    /**
     * Artifact ID for MavenCentral or similar.
     */
    const val artifact = "angelos-project-sig"

    /**
     * Group ID for MavenCentral or similar.
     * Can be the root package of the project.
     */
    const val group = "org.angproj.io.sig"

    /**
     * Version number of project.
     *
     * Should be based on semantic versioning as described at https://semver.org
     */
    const val version = "1.0.0-beta.1" //+ "-SNAPSHOT"

    /**
     * Licence name and URL to original.
     * No abbreviation.
     */
    const val licenceName = "The MIT License"
    const val licenceUrl = "https://opensource.org/licenses/MIT"

    /**
     * Project name for MavenCentral.
     */
    const val mavenName = "Signaling for Angelos Project™"

    /**
     * Long description for MavenCentral.
     */
    const val mavenDescription = "A signaling system made to be used without old legacy, in a modern asynchronous environment. With all necessary fundaments."

    /**
     * Maven scm url.
     */
    const val mavenScmUrl = "https://github.com/angelos-project/angelos-project-sig.git"

    /**
     * Maven scm connection.
     */
    const val mavenScmConnection = "scm:git:git://github.com/angelos-project/angelos-project-sig.git"

    /**
     * Maven scm developer connection.
     */
    const val mavenScmDeveloperConnection = "scm:git:git://github.com/angelos-project/angelos-project-sig.git"

    /**
     * MavenCentral snapshot repository.
     */
    const val mavenSnapshotRepo = "https://s01.oss.sonatype.org/content/repositories/snapshots/"

    /**
     * MavenCentral release repository.
     */
    const val mavenReleaseRepo = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"

    /**
     * Issue management.
     */
    const val issueManagement = "https://github.com/angelos-project/angelos-project-sig/issues"

    /**
     * Project native repository.
     */
    const val homeRepo = "https://github.com/angelos-project/angelos-project-sig"

    const val coroVersion = "1.6.3-native-mt"
    const val coroLibrary = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroVersion}"

    /**
     * Project wide Kotlin version.
     */
    const val kotlinVersion = "1.7.0" // "1.6.21"
    const val kotlinLibrary = "org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}"

    /**
     * Using the Dokka library of said version.
     */
    const val dokkaVersion = "1.7.0" // "1.6.21"
    const val dokkaLibrary = "org.jetbrains.dokka:dokka-gradle-plugin:${dokkaVersion}"

    /**
     * Using the Kover library of said version.
     */
    const val koverVersion = "0.5.0"
    const val koverLibrary = "org.jetbrains.kotlinx:kover:${koverVersion}"

    const val coverallsVersion = "1.2.14"
    const val coverallsLibrary = "com.github.nbaztec.coveralls-jacoco::${coverallsVersion}"
}

object MetaDevelopers {
    const val devID = "kristoffer-paulsson"
    const val devName = "Kristoffer Paulsson"
    const val devEmail = "kristoffer@angelos-project.com"

    const val devOrg = "Angelos Project™"
    const val devOrgUrl = "https://angelos-project.com"
}