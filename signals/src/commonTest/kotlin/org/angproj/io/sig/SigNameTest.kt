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

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse

/**
 * Signal name test class.
 *
 * @constructor Create empty Sig name test
 */
class SigNameTest {

    @Test
    fun codeToName() {
        assertFailsWith<UnsupportedSignalException> {
            SigName.codeToName(0)
        }
    }

    @Test
    fun nameToCode() {
        assertFailsWith<UnsupportedSignalException> {
            SigName.nameToCode(SigName.UNKNOWN)
        }
    }

    @Test
    fun isImplementedInt() {
        assertFalse(SigName.isImplemented(0))
    }

    @Test
    fun isImplementedEnum() {
        assertFalse(SigName.isImplemented(SigName.UNKNOWN))
    }

    @Test
    fun values() {
        for (value in SigName.values()) {
            if (SigName.isImplemented(value)) {
                println("✅ - ${SigName.nameToCode(value)}: $value")
            } else {
                println("❌ - n/a: $value")
            }
        }
    }
}