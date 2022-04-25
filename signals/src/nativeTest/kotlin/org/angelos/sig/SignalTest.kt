package org.angelos.sig

import co.touchlab.stately.concurrency.AtomicBoolean
import kotlinx.cinterop.memScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import platform.posix.raise
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

/**
 * Signal test class
 *
 * @constructor Create empty Signal test
 */
actual class SignalTest {

    /**
     * Register handler test.
     */
    @Test
    actual fun registerHandler() {
        val signal = SignalImpl()
        signal.registerHandler(SigName.SIGIO) { println("Hello signal $it") }
        assertFailsWith<SignalException> { signal.registerHandler(SigName.SIGKILL) { println("Hello signal $it") } }
    }

    /**
     * Catch signal test.
     */
    @Test
    actual fun catchInterrupt() {
        val received = AtomicBoolean(false)
        val signal = SignalImpl()
        signal.registerHandler(SigName.SIGUSR1) { received.value = true }
        runBlocking {
            launch {
                memScoped {
                    raise(SigName.nameToCode(SigName.SIGUSR1))
                }
            }
            delay(100)
        }
        assertTrue(received.value)
    }
}