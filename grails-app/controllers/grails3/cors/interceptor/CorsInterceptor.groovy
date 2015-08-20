package grails3.cors.interceptor

import grails.compiler.GrailsCompileStatic

/**
 * Add Cross-Origin Resource Sharing (CORS) headers for Grails applications. These headers make it possible for
 * Javascript code served from a different host to easily make calls to your application.
 *
 * @see https://github.com/davidtinker/grails-cors
 */
@GrailsCompileStatic
class CorsInterceptor {

    CorsInterceptor() {
        matchAll() // match all controllers
        //.excludes(controller:"login")   // uncomment to add exclusion
    }

    boolean before() {
        String origin = request.getHeader("Origin");
        boolean options = ("OPTIONS" == request.method)
        if (options) {
            header("Allow", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS")
            if (origin != null) {
                header("Access-Control-Allow-Headers", "origin, authorization, accept, content-type, x-requested-with")
                header("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS")
                header("Access-Control-Max-Age", "3600")
            }
        }

        header("Access-Control-Allow-Origin", origin ?: "*")
        header("Access-Control-Allow-Credentials", "true")

        true // proceed to controller
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }

}

