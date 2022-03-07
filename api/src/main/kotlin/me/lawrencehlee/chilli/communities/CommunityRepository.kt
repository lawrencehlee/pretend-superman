package me.lawrencehlee.chilli.communities

import org.springframework.data.repository.CrudRepository

interface CommunityRepository : CrudRepository<Community, Int>