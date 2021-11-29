package me.lawrencehlee.pretendsuperman.users

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(@Id val id: Int?, val username: String)
