package me.lawrencehlee.chilli.user

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import me.lawrencehlee.chilli.discord.DiscordUserRepository
import me.lawrencehlee.chilli.util.toNullable
import java.security.Principal

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("api/users")
class UserController(private val discordUserRepository: DiscordUserRepository) {
    @Get("@me")
    fun me(principal: Principal?): HttpResponse<Any> {
        val userId = principal?.name?.toLong() ?: return HttpResponse.unauthorized()

        val discordUser = discordUserRepository.findByUserId(userId).toNullable() ?: return HttpResponse.unauthorized()
        return HttpResponse.ok(
            DisplayUser(
                discordUser.user.id!!,
                discordUser.user.username,
                "${discordUser.username}#${discordUser.discriminator}"
            )
        )
    }
}