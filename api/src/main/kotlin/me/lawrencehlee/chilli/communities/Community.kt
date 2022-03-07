package me.lawrencehlee.chilli.communities

import me.lawrencehlee.chilli.users.User
import javax.persistence.*

@Entity
class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    open var id: Int? = null
    open var slug: String? = null
    open var name: String? = null

    @ManyToMany
    open var users: List<User>? = null
}