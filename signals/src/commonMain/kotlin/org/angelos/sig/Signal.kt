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
package org.angelos.sig

import co.touchlab.stately.collections.IsoMutableList
import co.touchlab.stately.collections.sharedMutableListOf
import co.touchlab.stately.collections.sharedMutableMapOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

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
        when(Internals.setInterrupt(sig)) {
            true -> when(sig in signals) {
                true -> signals[sig]!!.add(action)
                false -> signals[sig] = sharedMutableListOf(action)
            }
            false -> throw SignalException("Failed to install interrupt handler for $sig")
        }
    }

    companion object {
        private val scope = CoroutineScope(EmptyCoroutineContext)
        private val signals = sharedMutableMapOf<SigName, IsoMutableList<SignalHandler>>()

        internal inline fun catchInterrupt(sigName: SigName) = signals[sigName]?.forEach { it -> scope.launch { it(sigName) } }
    }
}