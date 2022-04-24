package org.angelos.sig

import kotlin.test.Test
import kotlin.test.assertFailsWith

class SignalImpl : Signal

class SignalTest {

    @Test
    fun registerHandler() {
        val signal = SignalImpl()
        signal.registerHandler(SigName.SIGIO) { println("Hello signal $it") }
        assertFailsWith<SignalException> { signal.registerHandler(SigName.SIGKILL) { println("Hello signal $it") } }
    }
}