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

//import sun.misc.Signal as SignalJVM

internal actual sealed class Internals {
    actual companion object {
        init {
            System.loadLibrary("jni-signals") // Load underlying library via JNI.
        }

        @JvmStatic
        private external fun sig_register(sigNum: Int): Long

        @JvmStatic
        private external fun sig_count(): Int

        @JvmStatic
        private external fun sig_code(index: Int): Int

        @JvmStatic
        private external fun sig_abbr(index: Int): String

        @JvmStatic
        private external fun sig_err(): Long

        actual fun sigCount(): Int = sig_count()

        actual fun sigCode(index: Int): Int = sig_code(index)

        actual fun sigAbbr(index: Int): String = sig_abbr(index)

        actual fun setInterrupt(sigName: SigName): Long {
            return sig_register(SigName.nameToCode(sigName))
        }

        actual fun sigErr(): Long = sig_err()
    }
}