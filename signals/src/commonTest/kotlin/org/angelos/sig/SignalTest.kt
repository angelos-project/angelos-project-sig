package org.angelos.sig

import kotlin.test.Test
import kotlin.test.assertFailsWith

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
class SignalTest {

    /**
     * Register handler test.
     */
    @Test
    fun registerHandler() {
        val signal = SignalImpl()
        signal.registerHandler(SigName.SIGIO) { println("Hello signal $it") }
        assertFailsWith<SignalException> { signal.registerHandler(SigName.SIGKILL) { println("Hello signal $it") } }
    }
}