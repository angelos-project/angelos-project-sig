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

#include <signal.h>

#include "c_signals.h"

#if defined (__linux__)
extern const char * const sys_sigabbrev[];
#endif

const char* empty = "";

const char* signal_abbr(int signum) {
#if defined (__FreeBSD__) || defined (__NetBSD__) || defined (__OpenBSD__) || defined (__APPLE__)
    const char* abbr = sys_signame[signum % NSIG];
#elif defined (__linux__)
    const char* abbr = sys_sigabbrev[signum % 32];
#elif defined (_WIN32) || defined (_WIN64)
    return
#endif
    return abbr ? abbr : empty;
}
