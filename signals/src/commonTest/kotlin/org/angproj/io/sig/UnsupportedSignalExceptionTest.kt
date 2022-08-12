package org.angproj.io.sig

import kotlin.test.Test
import kotlin.test.assertFailsWith

class UnsupportedSignalExceptionTest {
    @Test
    fun runTest() {
        assertFailsWith<UnsupportedSignalException> {
            throw UnsupportedSignalException("Test")
        }
    }
}