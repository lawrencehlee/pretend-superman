package me.lawrencehlee.pretendsuperman.communities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("communities")
data class Community(@Id val id: Int?, val slug: String, val name: String)
