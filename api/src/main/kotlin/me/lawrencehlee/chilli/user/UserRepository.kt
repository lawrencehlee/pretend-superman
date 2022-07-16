package me.lawrencehlee.chilli.user

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Repository
interface UserRepository : CrudRepository<User, Long>

@Entity(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var username: String
)
