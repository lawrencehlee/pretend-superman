package me.lawrencehlee.chilli.community

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("api/communities")
class CommunityController(private val communityRepository: CommunityRepository) {
    @Get
    fun query(): Iterable<Community> {
        return communityRepository.findAll()
    }

    @Post
    fun create(@Body community: Community): HttpStatus {
        communityRepository.save(community)
        return HttpStatus.CREATED
    }
}