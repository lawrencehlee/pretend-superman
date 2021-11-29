package me.lawrencehlee.pretendsuperman.communities

import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class CommunityDao(private val db: NamedParameterJdbcTemplate) {
    fun addUser(communityId: Int, userId: Int) {
        val sql = """
            INSERT INTO users_communities (community_id, user_id) VALUES (:communityId, :userId)
        """.trimIndent()

        db.update(sql, mapOf("communityId" to communityId, "userId" to userId))
    }

    fun queryByUserId(userId: Int): List<Community> {
        val sql = """
            SELECT communities.id, communities.slug, communities.name
            FROM communities
            JOIN users_communities ON communities.id = users_communities.community_id
            WHERE user_id = :userId
        """.trimIndent()

        return db.query(sql, mapOf("userId" to userId), DataClassRowMapper(Community::class.java))
    }
}