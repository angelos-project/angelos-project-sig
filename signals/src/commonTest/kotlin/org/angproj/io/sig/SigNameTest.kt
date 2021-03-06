package org.angproj.io.sig

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Signal name test class.
 *
 * @constructor Create empty Sig name test
 */
class SigNameTest {

    /**
     * Test for SigName.codeToName()
     */
    @Test
    fun codeToName() {
        if (Internals.sigAbbr(10) == "FAKE")
            return

        assertEquals(SigName.codeToName(6), SigName.SIGABRT)
        assertEquals(SigName.codeToName(14), SigName.SIGALRM)
        assertEquals(SigName.codeToName(8), SigName.SIGFPE)
        assertEquals(SigName.codeToName(1), SigName.SIGHUP)
        assertEquals(SigName.codeToName(4), SigName.SIGILL)
        assertEquals(SigName.codeToName(2), SigName.SIGINT)
        assertEquals(SigName.codeToName(9), SigName.SIGKILL)
        assertEquals(SigName.codeToName(13), SigName.SIGPIPE)
        assertEquals(SigName.codeToName(3), SigName.SIGQUIT)
        assertEquals(SigName.codeToName(11), SigName.SIGSEGV)
        assertEquals(SigName.codeToName(15), SigName.SIGTERM)
        assertEquals(SigName.codeToName(5), SigName.SIGTRAP)
    }

    /**
     * Test for SigName.nameToCode()
     */
    @Test
    fun nameToCode() {
        if (Internals.sigAbbr(10) == "FAKE")
            return

        assertEquals(SigName.nameToCode(SigName.SIGABRT), 6)
        assertEquals(SigName.nameToCode(SigName.SIGALRM), 14)
        assertEquals(SigName.nameToCode(SigName.SIGFPE), 8)
        assertEquals(SigName.nameToCode(SigName.SIGHUP), 1)
        assertEquals(SigName.nameToCode(SigName.SIGILL), 4)
        assertEquals(SigName.nameToCode(SigName.SIGINT), 2)
        assertEquals(SigName.nameToCode(SigName.SIGKILL), 9)
        assertEquals(SigName.nameToCode(SigName.SIGPIPE), 13)
        assertEquals(SigName.nameToCode(SigName.SIGQUIT), 3)
        assertEquals(SigName.nameToCode(SigName.SIGSEGV), 11)
        assertEquals(SigName.nameToCode(SigName.SIGTERM), 15)
        assertEquals(SigName.nameToCode(SigName.SIGTRAP), 5)
    }

    @Test
    fun testToString() {
        if (Internals.sigAbbr(10) == "FAKE")
            return

        assertEquals(SigName.SIGINT.toString(), "SIGINT")
    }
}