package com.iessanalberto.dam2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class ServerApiSpringApplication

fun main(args: Array<String>) {
	runApplication<ServerApiSpringApplication>(*args)
}
