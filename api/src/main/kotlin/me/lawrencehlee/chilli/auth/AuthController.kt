package me.lawrencehlee.chilli.auth

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import io.micronaut.security.token.jwt.cookie.JwtCookieClearerLogoutHandler
import io.micronaut.security.token.jwt.cookie.JwtCookieLoginHandler
import me.lawrencehlee.chilli.discord.DiscordClient
import me.lawrencehlee.chilli.discord.DiscordConfiguration
import me.lawrencehlee.chilli.discord.Token
import me.lawrencehlee.chilli.discord.UserWriter
import java.net.URI
import java.security.Principal

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("api")
class AuthController(
    private val discordClient: DiscordClient,
    private val userWriter: UserWriter,
    private val loginHandler: JwtCookieLoginHandler,
    private val logoutHandler: JwtCookieClearerLogoutHandler,
    private val discordConfiguration: DiscordConfiguration
) {
    @Get("oauth2/discord/redirect")
    suspend fun discordRedirect(
        @QueryValue code: String,
        @QueryValue state: String,
        httpRequest: HttpRequest<*>
    ): HttpResponse<Token> {
        if (code.isEmpty()) {
            return HttpResponse.redirect(URI("${discordConfiguration.postAuthBaseRedirectUri}"))
        }

        val token = discordClient.exchangeCode(code)
        val userId = userWriter.upsert(token)

        val auth = Authentication.build(userId.toString())
        val cookies = loginHandler.getCookies(auth, httpRequest).toSet()

        return HttpResponse.redirect<Token?>(URI("${discordConfiguration.postAuthBaseRedirectUri}?state=$state"))
            .cookies(cookies)
    }

    @Post("auth/logout")
    fun logout(principal: Principal?, httpRequest: HttpRequest<*>): HttpResponse<*> {
        val userId = principal?.name?.toLong() ?: return HttpResponse.unauthorized<Any>()
        userWriter.deleteToken(userId)
        return logoutHandler.logout(httpRequest)
    }
}