package me.lawrencehlee.pretendsuperman.users

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int>