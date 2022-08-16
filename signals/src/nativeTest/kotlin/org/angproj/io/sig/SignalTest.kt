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
package org.angproj.io.sig

import kotlinx.cinterop.memScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import platform.posix.raise
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

/**
 * Signal test class
 *
 * @constructor Create empty Signal test
 */
actual class SignalTest {

    @Test
    actual fun registerHandler() {
        val signal = SignalImpl()
        signal.registerHandler(SigName.SIGINT) { println("Hello signal $it") }
        assertFailsWith<UnsupportedSignalException> { signal.registerHandler(SigName.UNKNOWN) { println("Hello signal $it") } }
    }

    @Test
    actual fun catchInterrupt() {
        var received = false
        val signal = SignalImpl()
        signal.registerHandler(SigName.SIGUSR1) { received = true }
        runBlocking {
            launch {
                memScoped {
                    raise(SigName.nameToCode(SigName.SIGUSR1))
                }
            }
            delay(100)
        }
        assertTrue(received)
    }
}