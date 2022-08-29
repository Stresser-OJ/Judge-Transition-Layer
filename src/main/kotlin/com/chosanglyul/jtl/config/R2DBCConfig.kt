package com.chosanglyul.jtl.config

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration
import dev.miku.r2dbc.mysql.MySqlConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration

@Configuration
class R2DBCConfig(
    @Value("\${jtl.mysql.host}") private val host: String,
    @Value("\${jtl.mysql.password}") private val password: String,
    @Value("\${jtl.mysql.port}") private val port: Int,
    @Value("\${jtl.mysql.database}") private val database: String,
    @Value("\${jtl.mysql.username}") private val username: String,
) : AbstractR2dbcConfiguration() {
    @Bean
    override fun connectionFactory(): ConnectionFactory {
        return MySqlConnectionFactory.from(
            MySqlConnectionConfiguration.builder()
                .host(host).password(password).port(port).database(database).username(username).build()
        )
    }
}