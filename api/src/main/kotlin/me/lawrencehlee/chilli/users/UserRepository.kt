package me.lawrencehlee.chilli.users

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int>