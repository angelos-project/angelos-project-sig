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