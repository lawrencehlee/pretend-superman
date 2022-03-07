package me.lawrencehlee.chilli.users

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    open var id: Int? = null
    open var username: String? = null
}
