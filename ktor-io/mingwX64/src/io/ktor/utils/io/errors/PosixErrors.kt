package io.ktor.utils.io.errors

import io.ktor.utils.io.*
import kotlinx.cinterop.*

internal actual fun strerror_r(errnum: Int, msg: CArrayPointer<ByteVar>, size: _size_t): Int =
    platform.posix.strerror_s(msg, size.convert(), errnum)
