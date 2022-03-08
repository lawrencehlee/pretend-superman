package me.lawrencehlee.chilli.users

import me.lawrencehlee.chilli.common.idAsLong
import me.lawrencehlee.chilli.common.requireNotNull
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository

@Repository
class UserRepository(private val db: NamedParameterJdbcTemplate) {
    fun get(id: Long): User {
        val sql = """
            SELECT id, username FROM users WHERE id = :id
        """.trimIndent()

        return db.queryForObject(sql, mapOf("id" to id), DataClassRowMapper(User::class.java)).requireNotNull()
    }

    fun save(user: User): User {
        val sql = """
            INSERT INTO users (username) VALUES (:username)
        """.trimIndent()

        val keyHolder = GeneratedKeyHolder()
        db.update(sql, BeanPropertySqlParameterSource(user), keyHolder)
        return get(keyHolder.idAsLong())
    }
}