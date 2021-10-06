package io.ktor.utils.io.streams

import io.ktor.utils.io._ssize_t
import io.ktor.utils.io._size_t
import kotlinx.cinterop.*
import platform.posix.*

public expect class KX_SOCKET

public expect class KX_SOCKADDR_LEN : Number

@Suppress("NO_ACTUAL_FOR_EXPECT")
public expect class KX_SOCKADDR_LENVar : CPointed

public expect fun recv(__fd: KX_SOCKET, __buf: CValuesRef<*>?, __n: _size_t, __flags: Int): _ssize_t
public expect fun send(__fd: KX_SOCKET, __buf: CValuesRef<*>?, __n: _size_t, __flags: Int): _ssize_t

public expect fun recvfrom(
    __fd: KX_SOCKET,
    __buf: CValuesRef<*>?,
    __n: _size_t,
    __flags: Int,
    __addr: CValuesRef<sockaddr>?,
    __addr_len: CValuesRef<KX_SOCKADDR_LENVar>?
): _ssize_t

public expect fun sendto(
    __fd: KX_SOCKET,
    __buf: CValuesRef<*>?,
    __n: _size_t,
    __flags: Int,
    __addr: CValuesRef<sockaddr>?,
    __addr_len: KX_SOCKADDR_LEN
): _ssize_t
