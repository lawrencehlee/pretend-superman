package me.lawrencehlee.chilli.communities

import me.lawrencehlee.chilli.common.idAsLong
import me.lawrencehlee.chilli.common.requireNotNull
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository

@Repository
class CommunityRepository(private val db: NamedParameterJdbcTemplate) {
    fun get(id: Long): Community {
        val sql = """
            SELECT id, slug, name
            FROM communities
            WHERE id = :id
        """.trimIndent()

        return db.queryForObject(sql, mapOf("id" to id), DataClassRowMapper(Community::class.java)).requireNotNull()
    }

    fun addUser(communityId: Long, userId: Long): Boolean {
        val sql = """
            INSERT INTO users_communities (community_id, user_id) VALUES (:communityId, :userId) 
            ON CONFLICT (community_id, user_id) DO NOTHING 
        """.trimIndent()

        return db.update(sql, mapOf("communityId" to communityId, "userId" to userId)) > 0
    }

    fun queryByUserId(userId: Long): List<Community> {
        val sql = """
            SELECT communities.id, communities.slug, communities.name
            FROM communities
            JOIN users_communities ON communities.id = users_communities.community_id
            WHERE user_id = :userId
        """.trimIndent()

        return db.query(sql, mapOf("userId" to userId), DataClassRowMapper(Community::class.java))
    }

    fun save(community: Community): Community {
        val sql = """
            INSERT INTO communities (slug, name) VALUES (:slug, :name)
        """.trimIndent()

        val keyHolder = GeneratedKeyHolder()
        db.update(sql, BeanPropertySqlParameterSource(community), keyHolder)
        return get(keyHolder.idAsLong())
    }
}