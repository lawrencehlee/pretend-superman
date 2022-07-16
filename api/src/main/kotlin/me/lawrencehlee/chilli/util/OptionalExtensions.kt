package me.lawrencehlee.chilli.util

import java.util.*

fun <T> Optional<T>.toNullable(): T? {
    return this.orElse(null)
}