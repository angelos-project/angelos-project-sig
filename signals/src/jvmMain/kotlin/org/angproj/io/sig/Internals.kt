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

import sun.misc.Signal as SignalJVM

internal actual sealed class Internals {
    actual companion object {
        init {
            System.loadLibrary("jni-signals") // Load underlying library via JNI.
        }

        actual fun setInterrupt(sigName: SigName): Boolean {
            try {
                SignalJVM.handle(SignalJVM(sigName.sigName)) {
                    Signal.catchInterrupt(SigName.codeToName(it.number))
                }
            } catch (e: IllegalArgumentException) {
                return false
            }
            return true
        }

        actual fun sigAbbr(sigNum: Int): String = signal_abbreviation(sigNum).uppercase()

        @JvmStatic
        private external fun signal_abbreviation(sigNum: Int): String
    }
}