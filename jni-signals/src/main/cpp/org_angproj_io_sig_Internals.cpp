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
#include <jni.h>
#include <signal.h>
#include <stddef.h>

#include "c_signals.h"

#ifndef _Included_org_angproj_io_sig_Internals
#define _Included_org_angproj_io_sig_Internals
#ifdef __cplusplus
extern "C" {
#endif

static const char *JNIT_CLASS = "org/angproj/io/sig/Internals";


static JNIEnv * signal_jvm_env = NULL;
static jclass local_signal_cls = NULL;
static jclass global_signal_cls = NULL;
static jmethodID signal_method = NULL;


void sig_handler(int signum)
{
    (*signal_jvm_env)->CallStaticVoidMethod(signal_jvm_env, global_signal_cls, signal_method, signum);
}

/*
 * Class:     _Included_org_angproj_io_sig_Internals
 * Method:    sig_register
 * Signature: (I)J
 */
static jlong sig_register(JNIEnv * env, jclass thisClass, jint signum) {
    return (jlong) signal((int) signum, sig_handler);
}

/*
 * Class:     _Included_org_angproj_io_sig_Internals
 * Method:    sig_count
 * Signature: ()I
 */
static jint get_sig_count(JNIEnv * env, jclass thisClass) {
    return (jint) sig_count();
}

/*
 * Class:     _Included_org_angproj_io_sig_Internals
 * Method:    sig_code
 * Signature: (I)I
 */
static jint get_sig_code(JNIEnv * env, jclass thisClass, jint index) {
    return (jint) sig_code((unsigned int) index);
}

/*
 * Class:     _Included_org_angproj_io_sig_Internals
 * Method:    sig_abbr
 * Signature: (I)Ljava/lang/String;
 */
static jstring get_sig_abbr(JNIEnv * env, jclass thisClass, jint index){
    const char * abbr = sig_abbr((unsigned int) index);
    return (*env)->NewStringUTF(env, abbr);
}

/*
 * Class:     _Included_org_angproj_io_sig_Internals
 * Method:    sig_err
 * Signature: ()J
 */
static jint get_sig_err(JNIEnv * env, jclass thisClass) {
    return (jlong) SIG_ERR;
}

static JNINativeMethod funcs[] = {
    {"sig_register", "(I)J", (void *) &sig_register},

	{"sig_count", "()I", (void *) &get_sig_count},
	{"sig_code", "(I)I", (void *) &get_sig_code},
	{"sig_abbr", "(I)Ljava/lang/String;", (void *) &get_sig_abbr},
	{"sig_err", "()J", (void *) &get_sig_err}
};

#define CURRENT_JNI JNI_VERSION_1_6

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved)
{
	JNIEnv *env;
	jclass  cls;
	jint    res;

	(void)reserved;

	if ((*vm)->GetEnv(vm, (void **)&env, CURRENT_JNI) != JNI_OK)
		return -1;

	cls = (*env)->FindClass(env, JNIT_CLASS);
	if (cls == NULL)
		return -1;

	res = (*env)->RegisterNatives(env, cls, funcs, sizeof(funcs)/sizeof(*funcs));
	if (res != 0)
		return -1;

	// PREPARE STATIC START
	signal_jvm_env = env;
    local_signal_cls = (*signal_jvm_env)->FindClass(signal_jvm_env, "org/angproj/io/sig/Signal");
    global_signal_cls = (*signal_jvm_env)->NewGlobalRef(signal_jvm_env, local_signal_cls);
    signal_method = (*signal_jvm_env)->GetStaticMethodID(signal_jvm_env, global_signal_cls, "catchInterrupt", "(I)V");
    // PREPARE STATIC OVER

	return CURRENT_JNI;
}

JNIEXPORT void JNICALL JNI_OnUnload(JavaVM *vm, void *reserved)
{
	// PREPARE STATIC START
	signal_jvm_env = NULL;
    local_signal_cls = NULL;
    global_signal_cls = NULL;
    signal_method = NULL;
    // PREPARE STATIC OVER

	JNIEnv *env;
	jclass  cls;

	(void)reserved;

	if ((*vm)->GetEnv(vm, (void **)&env, CURRENT_JNI) != JNI_OK)
		return;

	cls = (*env)->FindClass(env, JNIT_CLASS);
	if (cls == NULL)
		return;

	(*env)->UnregisterNatives(env, cls);
}


#ifdef __cplusplus
}
#endif
#endif // _Included_org_angproj_io_sig_Internals