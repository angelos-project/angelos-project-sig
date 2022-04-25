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
package org.angelos.sig

import kotlinx.cinterop.*
import platform.posix.SIG_ERR
import platform.posix.signal
import csignals.signal_abbr

internal actual sealed class Internals {
    actual companion object {

        actual inline fun setInterrupt(sigName: SigName): Boolean = memScoped{
            signal(SigName.nameToCode(sigName), staticCFunction<Int, Unit> {
                Signal.catchInterrupt(SigName.codeToName(it))
                Unit
            }) != SIG_ERR
        }

        actual inline fun sigAbbr(sigNum: Int): String = memScoped {
            signal_abbr(sigNum)!!.toKString().uppercase()
        }
    }
}