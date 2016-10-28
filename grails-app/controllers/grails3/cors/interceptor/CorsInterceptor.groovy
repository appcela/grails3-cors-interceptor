package grails3.cors.interceptor

import grails.compiler.GrailsCompileStatic
import org.springframework.http.HttpStatus

/**
 * Add Cross-Origin Resource Sharing (CORS) headers for Grails applications. These headers make it possible for
 * Javascript code served from a different host to easily make calls to your application.
 *
 * @see https://github.com/davidtinker/grails-cors
 */
@GrailsCompileStatic
class CorsInterceptor {

    CorsService corsService

    CorsInterceptor() {
        matchAll() // match all controllers
        //.excludes(controller:"login")   // uncomment to add exclusion
    }

    boolean before() {
        !corsService.processPreflight(request, response)
    }

    boolean after() {
        // skip view rendering if it's OPTIONS request
        "OPTIONS" != request.method
    }

    void afterView() {
        // no-op
    }

}

