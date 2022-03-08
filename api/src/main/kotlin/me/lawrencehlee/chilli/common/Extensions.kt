package me.lawrencehlee.chilli.common

import org.springframework.jdbc.support.KeyHolder

fun <T> T?.requireNotNull(): T {
    return this ?: throw IllegalStateException("Really shouldn't be null")
}

fun KeyHolder.idAsLong(): Long {
    return keys?.get("id").requireNotNull() as Long
}