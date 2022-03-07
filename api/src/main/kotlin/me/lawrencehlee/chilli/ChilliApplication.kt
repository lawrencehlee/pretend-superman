package me.lawrencehlee.chilli

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ChilliApplication

fun main(args: Array<String>) {
    runApplication<ChilliApplication>(*args)
}
