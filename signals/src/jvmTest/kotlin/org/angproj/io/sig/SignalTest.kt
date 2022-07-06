package org.angproj.io.sig

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertTrue
import sun.misc.Signal as SignalJVM

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
        signal.registerHandler(SigName.SIGINT) { println("Hello signal $it") }
        //assertFailsWith<SignalException> { signal.registerHandler(SigName.SIGKILL) { println("Hello signal $it") } }
    }

    /**
     * Catch signal test.
     */
    @Test
    @Ignore
    actual fun catchInterrupt() {
        var received = false
        val signal = SignalImpl()
        signal.registerHandler(SigName.SIGUSR1) { received = true }
        runBlocking {
            launch {
                SignalJVM.raise(SignalJVM(SigName.SIGUSR1.sigName))
            }
            delay(250)
        }
        assertTrue(received)
    }
}