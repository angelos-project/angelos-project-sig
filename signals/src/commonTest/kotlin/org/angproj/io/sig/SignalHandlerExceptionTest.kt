package org.angproj.io.sig

import kotlin.test.Test
import kotlin.test.assertFailsWith

internal class SignalHandlerExceptionTest {
    @Test
    fun runTest() {
        assertFailsWith<SignalHandlerException> {
            throw SignalHandlerException("Test")
        }
    }
}