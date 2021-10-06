/*
 * Copyright 2014-2021 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package io.ktor.utils.io.streams

import io.ktor.utils.io.*
import kotlinx.cinterop.*
import platform.posix.*

public actual typealias KX_SOCKET = Int

// TODO: declaration is incompatible because modifiers are different (companion, inner, inline)
//  UInt has companion which isn't expected.
@Suppress("ACTUAL_WITHOUT_EXPECT")
public actual typealias KX_SOCKADDR_LEN = UInt

// TODO: declaration is incompatible because number of type parameters is different:
//  UIntVar is a friendly typealias to UIntVarOf<UInt>,
//  it is forbidden for some reason to have actual typealiases point to another typealias.
//  And also forbidden to have unmatched number of type parameters
@Suppress("ACTUAL_WITHOUT_EXPECT", "ACTUAL_TYPE_ALIAS_NOT_TO_CLASS")
public actual typealias KX_SOCKADDR_LENVar = UIntVar

public actual fun send(__fd: KX_SOCKET, __buf: CValuesRef<*>?, __n: _size_t, __flags: Int): _ssize_t =
    platform.posix.send(__fd, __buf, __n, __flags)

public actual fun recv(
    __fd: KX_SOCKET,
    __buf: CValuesRef<*>?,
    __n: _size_t,
    __flags: Int
): _ssize_t = platform.posix.recv(__fd, __buf, __n, __flags)

public actual fun recvfrom(
    __fd: KX_SOCKET,
    __buf: CValuesRef<*>?,
    __n: _size_t,
    __flags: Int,
    __addr: CValuesRef<sockaddr>?,
    __addr_len: CValuesRef<KX_SOCKADDR_LENVar>?
): _ssize_t = platform.posix.recvfrom(__fd, __buf, __n, __flags, __addr, __addr_len)

public actual fun sendto(
    __fd: KX_SOCKET,
    __buf: CValuesRef<*>?,
    __n: _size_t,
    __flags: Int,
    __addr: CValuesRef<sockaddr>?,
    __addr_len: KX_SOCKADDR_LEN
): _ssize_t = platform.posix.sendto(__fd, __buf, __n, __flags, __addr, __addr_len.convert())
