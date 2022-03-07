package me.lawrencehlee.chilli.communities

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("communities")
class CommunityController(
    private val communityRepository: CommunityRepository,
    private val communityDao: CommunityDao
) {
    @PostMapping
    fun create(@RequestBody community: Community): ResponseEntity<Community> {
        val created = communityRepository.save(community)
        return ResponseEntity.created(URI("/communities/${created.id}")).body(created)
    }

    @PutMapping("{communityId}/users/{userId}")
    fun addUser(@PathVariable communityId: Int, @PathVariable userId: Int): ResponseEntity<Any> {
        return ResponseEntity.noContent().build();
    }
}