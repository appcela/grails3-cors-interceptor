package grails3.cors.interceptor

import grails.compiler.GrailsCompileStatic
import grails.util.Environment

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
        Map corsInterceptorConfig = (Map) grailsApplication.config.corsInterceptor
        String[] includeEnvironments = corsInterceptorConfig['includeEnvironments']?: null
        String[] excludeEnvironments = corsInterceptorConfig['excludeEnvironments']?: null
        String[] allowedOrigins = corsInterceptorConfig['allowedOrigins']?: null

        if( excludeEnvironments && excludeEnvironments.contains(Environment.current.name) )  { // current env is excluded
            // skip
            return true
        }
        else if( includeEnvironments && !includeEnvironments.contains(Environment.current.name) )  {  // current env is not included
            // skip
            return true
        }


        String origin = request.getHeader("Origin");
        boolean options = ("OPTIONS" == request.method)
        if (options) {
            header("Allow", "GET, HEAD, POST, PUT, DELETE, TRACE, PATCH, OPTIONS")
            if (origin != null) {
                header("Access-Control-Allow-Headers", "origin, authorization, accept, content-type, x-requested-with")
                header("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, PATCH, OPTIONS")
                header("Access-Control-Max-Age", "3600")
            }
        }

        if( allowedOrigins && allowedOrigins.contains(origin)) { // request origin is on the white list
            // add CORS access control headers for the given origin
            header("Access-Control-Allow-Origin", origin)
            header("Access-Control-Allow-Credentials", "true")
        }
        else if( !allowedOrigins ) { // no origin white list
            // add CORS access control headers for all origins
            header("Access-Control-Allow-Origin", origin ?: "*")
            header("Access-Control-Allow-Credentials", "true")
        }

        !options // proceed to controller if method is not 'OPTIONS'
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }

}

