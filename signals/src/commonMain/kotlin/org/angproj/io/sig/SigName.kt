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

/**
 * All the POSIX signal names supported in this package.
 *
 * It is important to understand that all signals implemented doesn't represent all signals available.
 * Also, not every signal mentioned may not be supported by the underlying operating system.
 *
 * At software initialization all the signals up to 32 are probed against the OS.
 *
 * @property sigName
 * @constructor Create empty Sig name
 */
enum class SigName(val sigName: String){
    SIGABRT("ABRT"), // Process abort signal
    SIGALRM("ALRM"), // Alarm clock
    SIGBUS("BUS"), // Access to an undefined portion of a memory object
    SIGCHLD("CHLD"), // Child process terminated, stopped, or continued
    SIGCONT("CONT"), // Continue executing, if stopped
    SIGFPE("FPE"), // Erroneous arithmetic operation
    SIGHUP("HUP"), // Hangup
    SIGILL("ILL"), // Illegal instruction
    SIGINT("INT"), // Terminal interrupt signal
    SIGKILL("KILL"), // Kill (cannot be caught or ignored)
    SIGPIPE("PIPE"), // Write on a pipe with no one to read it
    SIGIO("IO"), // Pollable event
    SIGPOLL("POLL"), // Pollable event
    SIGPROF("PROF"), // Profiling timer expired
    SIGQUIT("QUIT"), // Terminal quit signal
    SIGSEGV("SEGV"), // Invalid memory reference
    SIGSTOP("STOP"), // Stop executing (cannot be caught or ignored)
    SIGSYS("SYS"), // Bad system call
    SIGTERM("TERM"), // Termination signal
    SIGTRAP("TRAP"), // Trace/breakpoint trap
    SIGTSTP("TSTP"), // Terminal stop signal
    SIGTTIN("TTIN"), // Background process attempting read
    SIGTTOU("TTOU"), // Background process attempting write
    SIGUSR1("USR1"), // User-defined signal 1
    SIGUSR2("USR2"), // User-defined signal 2
    SIGURG("URG"), // Out-of-band data is available at a socket
    SIGVTALRM("VTALRM"), // Virtual timer expired
    SIGXCPU("XCPU"), // CPU time limit exceeded
    SIGXFSZ("XFSZ"), // File size limit exceeded
    SIGWINCH("WINCH"), // Terminal window size changed


    UNKNOWN("UNKNOWN"); // Dummy for unauthorized use.

    /**
     * SigName as a representable string.
     *
     * @return Full signal name
     */
    override fun toString(): String = "SIG$sigName"

    companion object {
        private val numCache = mutableMapOf<Int, SigName>()
        private val nameCache = mutableMapOf<SigName, Int>()

        init {
            for(num in 1..32) {
                val abbr = Internals.sigAbbr(num)
                try {
                    val sig = valueOf("SIG$abbr")
                    numCache[num] = sig
                    nameCache[sig] = num
                } catch (_: Exception) {}
            }
        }

        /**
         * Convert a signal number to its equivalent signal name, throws SignalException if not supported.
         *
         * @param sigNum Signal number
         * @return Signal name
         */
        fun codeToName(sigNum: Int): SigName = numCache[sigNum] ?: throw SignalException("Unsupported signal number: $sigNum")

        /**
         * Convert signal name to its equivalent signal number, throws SignalException if not supported.
         *
         * @param sigName Signal name
         * @return Signal number
         */
        fun nameToCode(sigName: SigName): Int = nameCache[sigName] ?: throw SignalException("Unsupported signal: $sigName")

        /**
         * Check if signal number is implemented.
         *
         * @param sigNum Implemented or not
         */
        fun isImplemented(sigNum: Int) = numCache.containsKey(sigNum)

        /**
         * Check if signal name is implemented.
         *
         * @param sigName Implemented or not
         */
        fun isImplemented(sigName: SigName) = nameCache.containsKey(sigName)
    }
}