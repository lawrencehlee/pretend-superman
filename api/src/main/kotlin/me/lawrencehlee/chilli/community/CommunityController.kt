package me.lawrencehlee.chilli.community

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import org.slf4j.LoggerFactory

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("api/communities")
class CommunityController(private val communityRepository: CommunityRepository) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Get
    fun query(authentication: Authentication): Iterable<Community> {
        logger.info(authentication.toString())
        return communityRepository.findAll()
    }

    @Post
    fun create(@Body community: Community): HttpStatus {
        communityRepository.save(community)
        return HttpStatus.CREATED
    }
}