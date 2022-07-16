package me.lawrencehlee.chilli.discord

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.micronaut.http.HttpRequest.POST
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import jakarta.inject.Singleton

@Singleton
class DiscordClient(
    @Client("https://discord.com/api/v8") private val client: HttpClient,
    private val discordConfiguration: DiscordConfiguration
) {
    fun exchangeCode(code: String): Token {
        val body = mapOf(
            "client_id" to discordConfiguration.clientId,
            "client_secret" to discordConfiguration.clientSecret,
            "code" to code,
            "grant_type" to "authorization_code",
            "redirect_uri" to discordConfiguration.redirectUri
        )

        return client.toBlocking().retrieve(
            POST("oauth2/token", body).contentType(MediaType.APPLICATION_FORM_URLENCODED),
            Token::class.java
        ) ?: throw IllegalStateException("Null token response body")
    }

    fun getNewToken(refreshToken: String): Token {
        val body = mapOf(
            "client_id" to discordConfiguration.clientId,
            "client_secret" to discordConfiguration.clientSecret,
            "grant_type" to "refresh_token",
            "refresh_token" to refreshToken
        )

        return client.toBlocking().retrieve(
            POST("oauth2/token", body).contentType(MediaType.APPLICATION_FORM_URLENCODED),
            Token::class.java
        ) ?: throw IllegalStateException("Null token response body")
    }
}

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Token(
    val accessToken: String,
    val refreshToken: String,
)