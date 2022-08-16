/**
 * Copyright (c) 2021-2022 by Kristoffer Paulsson <kristoffer.paulsson@talenten.se>.
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

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.angproj.io.err.AbstractError
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmStatic

/**
 * Signal interface, can be implemented on any object or class.
 * Implements all necessary functionality.
 *
 * @constructor Create empty Signal
 */
interface Signal {

    /**
     * Register handler in a certain POSIX signal.
     *
     * @param sig Signal name to handle
     * @param action Lambda dealing with the incoming signal
     */
    fun registerHandler(sig: SigName, action: SignalHandler) {
        when (Internals.setInterrupt(sig)) {
            SIG_ERR -> throw SignalHandlerException(
                AbstractError.error("Installing signal handler for $sig")
            )

            else -> {
                when (sig in signals) {
                    true -> signals[sig]!!.add(action)
                    false -> signals[sig] = mutableListOf(action)
                }
            }
        }
    }

    companion object {
        private val scope = CoroutineScope(EmptyCoroutineContext)
        private val signals = mutableMapOf<SigName, MutableList<SignalHandler>>()
        private val SIG_ERR = Internals.sigErr()

        /**
         * Is the callback to which POSIX is sending the signal.
         * This function is strictly only to be used with catching signals,
         * however misuse can lead to undefined behavior.
         *
         * @param sigNum The signal code to listen for.
         */
        @JvmStatic
        fun catchInterrupt(sigNum: Int) {
            val sigName = SigName.codeToName(sigNum)
            signals[sigName]?.forEach { it -> scope.launch { it(sigName) } }
        }
    }
}