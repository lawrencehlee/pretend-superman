package me.lawrencehlee.chilli.discord

import dev.kord.rest.request.KtorRequestHandler
import dev.kord.rest.service.RestClient
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import jakarta.inject.Singleton
import me.lawrencehlee.chilli.user.User
import me.lawrencehlee.chilli.user.UserRepository
import java.util.*

@Singleton
open class UserWriter(
    private val userRepository: UserRepository,
    private val discordUserRepository: DiscordUserRepository
) {
    /**
     * Returns the id of the actual user
     */
    suspend fun upsert(token: Token): Long {
        val restClient = RestClient(ktorRequestHandler(token.accessToken))
        val externalDiscordUser: dev.kord.common.entity.DiscordUser = restClient.user.getCurrentUser()
        val existingDiscordUser: Optional<DiscordUser> = discordUserRepository.findById(externalDiscordUser.id.asString)

        if (existingDiscordUser.isPresent) {
            val unwrapped = existingDiscordUser.get()
            unwrapped.username = externalDiscordUser.username
            unwrapped.refreshToken = token.refreshToken
            discordUserRepository.update(unwrapped)

            return unwrapped.user.id!!
        } else {
            val createdUser = userRepository.save(User(null, externalDiscordUser.username))
            discordUserRepository.save(
                DiscordUser(
                    externalDiscordUser.id.asString,
                    token.refreshToken,
                    externalDiscordUser.username,
                    externalDiscordUser.discriminator,
                    externalDiscordUser.email.value,
                    createdUser
                )
            )

            return createdUser.id!!
        }
    }

    fun deleteToken(userId: Long) {
        val existingDiscordUser: DiscordUser = discordUserRepository.findByUserId(userId)
            .orElseThrow { IllegalArgumentException("User $userId not found") }
        existingDiscordUser.refreshToken = null
        discordUserRepository.update(existingDiscordUser)
    }
}

fun ktorRequestHandler(token: String): KtorRequestHandler {
    val client = HttpClient(CIO) {
        expectSuccess = false
        defaultRequest { header("Authorization", "Bearer $token") }
    }
    return KtorRequestHandler(client)
}
