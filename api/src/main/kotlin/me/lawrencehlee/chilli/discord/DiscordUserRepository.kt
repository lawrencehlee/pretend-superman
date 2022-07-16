package me.lawrencehlee.chilli.discord

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import me.lawrencehlee.chilli.user.User
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Repository
interface DiscordUserRepository : CrudRepository<DiscordUser, String> {
    fun findByUserId(userId: Long): Optional<DiscordUser>
}

@Entity(name = "discord_users")
data class DiscordUser(
    @Id
    var id: String,
    @Column(name = "refresh_token")
    var refreshToken: String?,
    var username: String,
    var discriminator: String,
    var email: String?,
    @OneToOne
    var user: User
)