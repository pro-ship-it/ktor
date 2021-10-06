/*
 * Copyright 2014-2021 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package io.ktor.utils.io.streams

import io.ktor.utils.io.*
import kotlinx.cinterop.*
import platform.posix.*

//
//public actual val SSIZE_MAX: _ssize_t = platform.posix.SSIZE_MAX.convert()

// TODO: declaration is incompatible because modifiers are different (companion, inner, inline)
//  ULong has companion which isn't expected.
@Suppress("ACTUAL_WITHOUT_EXPECT")
// TODO: actually it should be typealias to platform.posix.SOCKET but it isn't permitted
//  to have actual pointing to typealias
public actual typealias KX_SOCKET = ULong // = SOCKET

public actual typealias KX_SOCKADDR_LEN = Int

// TODO: declaration is incompatible because number of type parameters is different:
//  IntVar is a friendly typealias to IntVarOf<Int>,
//  it is forbidden for some reason to have actual typealiases point to another typealias.
//  And also forbidden to have unmatched number of type parameters
@Suppress("ACTUAL_WITHOUT_EXPECT", "ACTUAL_TYPE_ALIAS_NOT_TO_CLASS")
public actual typealias KX_SOCKADDR_LENVar = IntVar

public actual fun recv(__fd: KX_SOCKET, __buf: CValuesRef<*>?, __n: _size_t, __flags: Int): _ssize_t =
    platform.posix.recv(__fd, __buf as CValuesRef<ByteVar>? /* TODO: Is it safe? */, __n.convert(), __flags).convert()

public actual fun recvfrom(
    __fd: KX_SOCKET,
    __buf: CValuesRef<*>?,
    __n: _size_t,
    __flags: Int,
    __addr: CValuesRef<sockaddr>?,
    __addr_len: CValuesRef<KX_SOCKADDR_LENVar>?
): _ssize_t =
    platform.posix.recvfrom(__fd, __buf as CValuesRef<ByteVar>?, __n.convert(), __flags, __addr, __addr_len).convert()

public actual fun send(__fd: KX_SOCKET, __buf: CValuesRef<*>?, __n: _size_t, __flags: Int): _ssize_t =
    platform.posix.send(__fd, __buf as CValuesRef<ByteVar>?, __n.convert(), __flags).convert()

public actual fun sendto(
    __fd: KX_SOCKET,
    __buf: CValuesRef<*>?,
    __n: _size_t,
    __flags: Int,
    __addr: CValuesRef<sockaddr>?,
    __addr_len: KX_SOCKADDR_LEN
): _ssize_t =
    platform.posix.sendto(__fd, __buf as CValuesRef<ByteVar>?, __n.convert(), __flags, __addr, __addr_len.convert()).convert()
