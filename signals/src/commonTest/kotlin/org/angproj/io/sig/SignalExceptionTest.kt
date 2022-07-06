package org.angproj.io.sig

import kotlin.test.Test
import kotlin.test.assertFailsWith


class SignalExceptionTest {

    @Test
    fun exception() {
        assertFailsWith<SignalException> {
            throw SignalException("Test")
        }
    }
}