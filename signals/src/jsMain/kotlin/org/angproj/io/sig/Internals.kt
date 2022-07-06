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

internal actual sealed class Internals {
    actual companion object {
        actual fun setInterrupt(sigName: SigName): Boolean {
            return false
            // TODO("Not yet implemented")
        }

        actual fun sigAbbr(sigNum: Int): String {
            return "FAKE"
            // TODO("Not yet implemented")
        }
    }
}