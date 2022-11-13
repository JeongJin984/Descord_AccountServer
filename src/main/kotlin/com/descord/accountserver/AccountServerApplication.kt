package com.descord.accountserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AccountServerApplication

fun main(args: Array<String>) {
    runApplication<AccountServerApplication>(*args)
}
