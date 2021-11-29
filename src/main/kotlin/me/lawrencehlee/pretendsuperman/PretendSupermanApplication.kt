package me.lawrencehlee.pretendsuperman

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class PretendSupermanApplication

fun main(args: Array<String>) {
    runApplication<PretendSupermanApplication>(*args)
}
