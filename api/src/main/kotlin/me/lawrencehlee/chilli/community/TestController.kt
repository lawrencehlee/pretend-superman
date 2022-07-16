package me.lawrencehlee.chilli.community

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("api/test")
class TestController {
    @Get
    fun test(): String {
        return "hello"
    }
}