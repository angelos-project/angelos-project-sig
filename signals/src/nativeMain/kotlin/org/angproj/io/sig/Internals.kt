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

import csignals.sig_abbr
import csignals.sig_code
import csignals.sig_count
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.staticCFunction
import kotlinx.cinterop.toKString
import kotlinx.cinterop.toLong
import platform.posix.SIG_ERR
import platform.posix.signal

internal actual sealed class Internals {
    actual companion object {

        actual inline fun setInterrupt(sigName: SigName): Long = memScoped {
            signal(SigName.nameToCode(sigName), staticCFunction<Int, Unit> {
                Signal.catchInterrupt(it)
            }).toLong()
        }

        actual inline fun sigCount(): Int = sig_count().toInt()

        actual inline fun sigCode(index: Int): Int = sig_code(index.toUInt()).toInt()

        actual inline fun sigAbbr(index: Int): String = sig_abbr(index.toUInt())?.toKString() ?: ""

        actual fun sigErr(): Long = SIG_ERR.toLong()
    }
}