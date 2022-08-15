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
enum class SigName(val sigName: String) {
    SIGABRT("SIGABRT"),
    SIGALRM("SIGALRM"),
    SIGBUS("SIGBUS"),
    SIGCHLD("SIGCHLD"),
    SIGCONT("SIGCONT"),
    SIGFPE("SIGFPE"),
    SIGHUP("SIGHUP"),
    SIGILL("SIGILL"),
    SIGINT("SIGINT"),
    SIGKILL("SIGKILL"),
    SIGPIPE("SIGPIPE"),
    SIGQUIT("SIGQUIT"),
    SIGSEGV("SIGSEGV"),
    SIGSTOP("SIGSTOP"),
    SIGTERM("SIGTERM"),
    SIGTSTP("SIGTSTP"),
    SIGTTIN("SIGTTIN"),
    SIGTTOU("SIGTTOU"),
    SIGUSR1("SIGUSR1"),
    SIGUSR2("SIGUSR2"),
    SIGPOLL("SIGPOLL"),
    SIGPROF("SIGPROF"),
    SIGSYS("SIGSYS"),
    SIGTRAP("SIGTRAP"),
    SIGURG("SIGURG"),
    SIGVTALRM("SIGVTALRM"),
    SIGXCPU("SIGXCPU"),
    SIGXFSZ("SIGXFSZ"),
    SIGIO("SIGIO"),
    SIGIOT("SIGIOT"),
    SIGRTMIN("SIGRTMIN"),
    SIGRTMAX("SIGRTMAX"),
    SIGWINCH("SIGWINCH"),
    SIGEMT("SIGEMT"),
    SIGINFO("SIGINFO"),
    SIGPWR("SIGPWR"),
    SIGLOST("SIGLOST"),
    SIGSTKFLT("SIGSTKFLT"),
    SIGUNUSED("SIGUNUSED"),
    SIGCLD("SIGCLD"),
    UNKNOWN("UNKNOWN");

    companion object {
        private val numCache = mutableMapOf<Int, SigName>()
        private val nameCache = mutableMapOf<SigName, Int>()

        init {
            for (num in 0..Internals.sigCount()) {
                val code = Internals.sigCode(num)
                if (code != 0 && !numCache.containsKey(code)) {
                    try {
                        val abbr = valueOf(Internals.sigAbbr(num))
                        numCache[code] = abbr
                        nameCache[abbr] = code
                    } catch (_: Exception) {
                    }
                }
            }
        }

        /**
         * Convert a signal number to its equivalent signal name, throws SignalException if not supported.
         *
         * @param sigNum Signal number
         * @return Signal name
         */
        fun codeToName(sigNum: Int): SigName =
            numCache[sigNum] ?: throw UnsupportedSignalException("Unsupported signal number: $sigNum")

        /**
         * Convert signal name to its equivalent signal number, throws SignalException if not supported.
         *
         * @param sigName Signal name
         * @return Signal number
         */
        fun nameToCode(sigName: SigName): Int =
            nameCache[sigName] ?: throw UnsupportedSignalException("Unsupported signal: $sigName")

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