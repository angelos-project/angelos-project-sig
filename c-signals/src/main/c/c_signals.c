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
#include <stddef.h>

#include "c_signals.h"

static unsigned int const count = 38;
static char const * const names[] =
{

    #ifdef SIGABRT
    "SIGABRT",
    #else
    NULL,
    #endif

    #ifdef SIGALRM
    "SIGALRM",
    #else
    NULL,
    #endif

    #ifdef SIGBUS
    "SIGBUS",
    #else
    NULL,
    #endif

    #ifdef SIGCHLD
    "SIGCHLD",
    #else
    NULL,
    #endif

    #ifdef SIGCONT
    "SIGCONT",
    #else
    NULL,
    #endif

    #ifdef SIGFPE
    "SIGFPE",
    #else
    NULL,
    #endif

    #ifdef SIGHUP
    "SIGHUP",
    #else
    NULL,
    #endif

    #ifdef SIGILL
    "SIGILL",
    #else
    NULL,
    #endif

    #ifdef SIGINT
    "SIGINT",
    #else
    NULL,
    #endif

    #ifdef SIGKILL
    "SIGKILL",
    #else
    NULL,
    #endif

    #ifdef SIGPIPE
    "SIGPIPE",
    #else
    NULL,
    #endif

    #ifdef SIGQUIT
    "SIGQUIT",
    #else
    NULL,
    #endif

    #ifdef SIGSEGV
    "SIGSEGV",
    #else
    NULL,
    #endif

    #ifdef SIGSTOP
    "SIGSTOP",
    #else
    NULL,
    #endif

    #ifdef SIGTERM
    "SIGTERM",
    #else
    NULL,
    #endif

    #ifdef SIGTSTP
    "SIGTSTP",
    #else
    NULL,
    #endif

    #ifdef SIGTTIN
    "SIGTTIN",
    #else
    NULL,
    #endif

    #ifdef SIGTTOU
    "SIGTTOU",
    #else
    NULL,
    #endif

    #ifdef SIGUSR1
    "SIGUSR1",
    #else
    NULL,
    #endif

    #ifdef SIGUSR2
    "SIGUSR2",
    #else
    NULL,
    #endif

    #ifdef SIGPOLL
    "SIGPOLL",
    #else
    NULL,
    #endif

    #ifdef SIGPROF
    "SIGPROF",
    #else
    NULL,
    #endif

    #ifdef SIGSYS
    "SIGSYS",
    #else
    NULL,
    #endif

    #ifdef SIGTRAP
    "SIGTRAP",
    #else
    NULL,
    #endif

    #ifdef SIGURG
    "SIGURG",
    #else
    NULL,
    #endif

    #ifdef SIGVTALRM
    "SIGVTALRM",
    #else
    NULL,
    #endif

    #ifdef SIGXCPU
    "SIGXCPU",
    #else
    NULL,
    #endif

    #ifdef SIGXFSZ
    "SIGXFSZ",
    #else
    NULL,
    #endif

    #ifdef SIGIO
    "SIGIO",
    #else
    NULL,
    #endif

    #ifdef SIGIOT
    "SIGIOT",
    #else
    NULL,
    #endif

    #ifdef SIGWINCH
    "SIGWINCH",
    #else
    NULL,
    #endif

    #ifdef SIGEMT
    "SIGEMT",
    #else
    NULL,
    #endif

    #ifdef SIGINFO
    "SIGINFO",
    #else
    NULL,
    #endif

    #ifdef SIGPWR
    "SIGPWR",
    #else
    NULL,
    #endif

    #ifdef SIGLOST
    "SIGLOST",
    #else
    NULL,
    #endif

    #ifdef SIGSTKFLT
    "SIGSTKFLT",
    #else
    NULL,
    #endif

    #ifdef SIGUNUSED
    "SIGUNUSED",
    #else
    NULL,
    #endif

    #ifdef SIGCLD
    "SIGCLD",
    #else
    NULL,
    #endif
};


static unsigned int const codes[] =
{
    #ifdef SIGABRT
    SIGABRT,
    #else
    0,
    #endif

    #ifdef SIGALRM
    SIGALRM,
    #else
    0,
    #endif

    #ifdef SIGBUS
    SIGBUS,
    #else
    0,
    #endif

    #ifdef SIGCHLD
    SIGCHLD,
    #else
    0,
    #endif

    #ifdef SIGCONT
    SIGCONT,
    #else
    0,
    #endif

    #ifdef SIGFPE
    SIGFPE,
    #else
    0,
    #endif

    #ifdef SIGHUP
    SIGHUP,
    #else
    0,
    #endif

    #ifdef SIGILL
    SIGILL,
    #else
    0,
    #endif

    #ifdef SIGINT
    SIGINT,
    #else
    0,
    #endif

    #ifdef SIGKILL
    SIGKILL,
    #else
    0,
    #endif

    #ifdef SIGPIPE
    SIGPIPE,
    #else
    0,
    #endif

    #ifdef SIGQUIT
    SIGQUIT,
    #else
    0,
    #endif

    #ifdef SIGSEGV
    SIGSEGV,
    #else
    0,
    #endif

    #ifdef SIGSTOP
    SIGSTOP,
    #else
    0,
    #endif

    #ifdef SIGTERM
    SIGTERM,
    #else
    0,
    #endif

    #ifdef SIGTSTP
    SIGTSTP,
    #else
    0,
    #endif

    #ifdef SIGTTIN
    SIGTTIN,
    #else
    0,
    #endif

    #ifdef SIGTTOU
    SIGTTOU,
    #else
    0,
    #endif

    #ifdef SIGUSR1
    SIGUSR1,
    #else
    0,
    #endif

    #ifdef SIGUSR2
    SIGUSR2,
    #else
    0,
    #endif

    #ifdef SIGPOLL
    SIGPOLL,
    #else
    0,
    #endif

    #ifdef SIGPROF
    SIGPROF,
    #else
    0,
    #endif

    #ifdef SIGSYS
    SIGSYS,
    #else
    0,
    #endif

    #ifdef SIGTRAP
    SIGTRAP,
    #else
    0,
    #endif

    #ifdef SIGURG
    SIGURG,
    #else
    0,
    #endif

    #ifdef SIGVTALRM
    SIGVTALRM,
    #else
    0,
    #endif

    #ifdef SIGXCPU
    SIGXCPU,
    #else
    0,
    #endif

    #ifdef SIGXFSZ
    SIGXFSZ,
    #else
    0,
    #endif

    #ifdef SIGIO
    SIGIO,
    #else
    0,
    #endif

    #ifdef SIGIOT
    SIGIOT,
    #else
    0,
    #endif

    #ifdef SIGWINCH
    SIGWINCH,
    #else
    0,
    #endif

    #ifdef SIGEMT
    SIGEMT,
    #else
    0,
    #endif

    #ifdef SIGINFO
    SIGINFO,
    #else
    0,
    #endif

    #ifdef SIGPWR
    SIGPWR,
    #else
    0,
    #endif

    #ifdef SIGLOST
    SIGLOST,
    #else
    0,
    #endif

    #ifdef SIGSTKFLT
    SIGSTKFLT,
    #else
    0,
    #endif

    #ifdef SIGUNUSED
    SIGUNUSED,
    #else
    0,
    #endif

    #ifdef SIGCLD
    SIGCLD,
    #else
    0,
    #endif
};


unsigned int sig_count() {
    return count;
}

unsigned int sig_code(unsigned int index) {
    if (index >= count) {
        return 0;
    } else {
        return codes[index];
    }
}

const char * sig_abbr(unsigned int index) {
    if (index >= count) {
        return (void *) 0;
    } else {
        return names[index];
    }
}