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

object Project {
    const val name = "angelos-project-sig"
    const val version = "1.0a"
}

object Versions {
    const val gradle = "7.3.3"
    const val kotlin = "1.6.10"
    const val dokka = "1.6.10"
    const val coro = "1.6.0"
    const val stately = "1.2.0"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    const val coro = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coro}-native-mt"
    const val stately = "co.touchlab:stately-common:${Versions.stately}"
    const val concurrency = "co.touchlab:stately-concurrency:${Versions.stately}"
    const val collections = "co.touchlab:stately-iso-collections:${Versions.stately}"
}

object TestLibs {
    const val coro = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coro}"
}