package me.lawrencehlee.chilli.discord

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("discord")
class DiscordConfiguration {
    var clientId: String = ""
    var clientSecret: String = ""
    var redirectUri: String = ""
    var postAuthBaseRedirectUri: String = ""
}