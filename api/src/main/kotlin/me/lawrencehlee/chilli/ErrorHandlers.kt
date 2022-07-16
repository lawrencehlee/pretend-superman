package me.lawrencehlee.chilli

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.hateoas.JsonError
import org.slf4j.LoggerFactory

@Controller
class ErrorController {
    private val logger = LoggerFactory.getLogger(this::class.java)

    //    @Error(global = true, status = HttpStatus.INTERNAL_SERVER_ERROR)
    fun internalServerError(request: HttpRequest<*>, e: Throwable): HttpResponse<JsonError> {
        logger.error("Internal server error", e)
        return HttpResponse.serverError<JsonError?>().body(JsonError("Internal server error occurred"))
    }
}