package me.lawrencehlee.chilli.community

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("communities")
class CommunityController() {
    @Get
    fun index(): Any {
        return object {
            val hello = "world"
        }
    }

//    fun create(@RequestBody community: Community): ResponseEntity<Community> {
//        val created = communityRepository.save(community)
//        return ResponseEntity.created(URI("/communities/${created.id}")).body(created)
//    }

//    @PutMapping("{communityId}/users/{userId}")
//    fun addUser(@PathVariable communityId: Long, @PathVariable userId: Long): ResponseEntity<Any> {
//        communityRepository.addUser(communityId, userId)
//        return ResponseEntity.noContent().build();
//    }
}