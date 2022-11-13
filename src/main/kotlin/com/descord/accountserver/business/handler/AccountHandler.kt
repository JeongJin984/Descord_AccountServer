package com.descord.accountserver.business.handler

import com.descord.accountserver.data.domain.Account
import io.r2dbc.spi.ConnectionFactory
import org.springframework.data.annotation.Id
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Controller
class AccountHandler(
    val connectionFactory: ConnectionFactory
) {

    @MessageMapping("find")
    fun getAccount(@Payload accountId: Long): Mono<Account> {
        val databaseClient: DatabaseClient = DatabaseClient.create(connectionFactory)

        return databaseClient
            .sql("select * from chatting_account ca where ca.id = :userId")
            .bind("userId", accountId)
            .map { row -> Account(
                row.get("id").toString().toLong(),
                row.get("name").toString()
            ) }
            .one()
            .log()
    }

    data class UserServerJoin(val id: Long, val userId: Long, val userLevel: String)
}