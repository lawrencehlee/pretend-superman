package me.lawrencehlee.chilli.community

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "communities")
data class Community(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    var slug: String,
    var name: String,
)

@Repository
interface CommunityRepository : CrudRepository<Community, Int>