package grails3.cors.interceptor

import grails.transaction.Transactional
import grails.util.Environment

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Transactional
class CorsService {
    def grailsApplication

    boolean processPreflight(HttpServletRequest request, HttpServletResponse response) {
        Map corsInterceptorConfig = (Map) grailsApplication.config.corsInterceptor

        String[] includeEnvironments = corsInterceptorConfig['includeEnvironments']?: null
        String[] excludeEnvironments = corsInterceptorConfig['excludeEnvironments']?: null
        String[] allowedOrigins = corsInterceptorConfig['allowedOrigins']?: null
        String[] allowedHeaders = corsInterceptorConfig['allowedHeaders']?: ["origin", "authorization", "accept", "content-type", "x-requested-with"]

        if( excludeEnvironments && excludeEnvironments.contains(Environment.current.name) )  { // current env is excluded
            // skip
            return false
        }
        else if( includeEnvironments && !includeEnvironments.contains(Environment.current.name) )  {  // current env is not included
            // skip
            return false
        }

        String origin = request.getHeader("Origin");
        boolean options = ("OPTIONS" == request.method)
        if (options) {
            response.addHeader("Allow", "GET, HEAD, POST, PUT, DELETE, TRACE, PATCH, OPTIONS")
            if (origin != null) {
                response.setHeader("Access-Control-Allow-Headers", allowedHeaders.join(", "))
                response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, PATCH, OPTIONS")
                response.setHeader("Access-Control-Max-Age", "3600")
            }
        }

        if( allowedOrigins && allowedOrigins.contains(origin)) { // request origin is on the white list
            // add CORS access control headers for the given origin
            response.setHeader("Access-Control-Allow-Origin", origin)
            response.setHeader("Access-Control-Allow-Credentials", "true")
        }
        else if( !allowedOrigins ) { // no origin white list
            // add CORS access control headers for all origins
            response.setHeader("Access-Control-Allow-Origin", origin ?: "*")
            response.setHeader("Access-Control-Allow-Credentials", "true")
        }

        options
    }
}
