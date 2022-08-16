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

/**
 * Signal implementation for testing
 *
 * @constructor Create empty Signal impl
 */
class SignalImpl : Signal

/**
 * Signal test class
 *
 * @constructor Create empty Signal test
 */
expect class SignalTest {

    /**
     * Register handler test.
     */
    @Test
    fun registerHandler()

    /**
     * Catch signal test.
     */
    @Test
    fun catchInterrupt()
}