package me.lawrencehlee.chilli.users

import me.lawrencehlee.chilli.communities.Community
import me.lawrencehlee.chilli.communities.CommunityRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class UserController(private val userRepository: UserRepository, private val communityRepository: CommunityRepository) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun create(@RequestBody user: User): User {
        return userRepository.save(user)
    }

    @GetMapping("{userId}/communities")
    fun getCommunities(@PathVariable userId: Long): List<Community> {
        return communityRepository.queryByUserId(userId)
    }
}