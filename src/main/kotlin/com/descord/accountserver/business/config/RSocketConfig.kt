package com.descord.accountserver.business.config

import com.descord.accountserver.business.handler.AccountHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.cbor.Jackson2CborDecoder
import org.springframework.http.codec.cbor.Jackson2CborEncoder
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.RSocketStrategies
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler
import org.springframework.web.util.pattern.PathPatternRouteMatcher

@Configuration
class RSocketConfig(
    val accountHandler: AccountHandler
) {
    @Bean
    fun getRocketRequester() : RSocketRequester {
        val strategies = RSocketStrategies.builder()
            .encoders { it.add(Jackson2CborEncoder()) }
            .decoders { it.add(Jackson2CborDecoder()) }
            .routeMatcher(PathPatternRouteMatcher())
            .build()

        // Client Responder : Connection을 요청해서 Request를 받는 쪽
        val responder =
            RSocketMessageHandler.responder(strategies, accountHandler);

        val builder : RSocketRequester.Builder = RSocketRequester.builder()
        return builder
            .rsocketStrategies(strategies)
            .rsocketConnector {it.acceptor(responder)}
            .tcp("localhost", 7000)
    }
}