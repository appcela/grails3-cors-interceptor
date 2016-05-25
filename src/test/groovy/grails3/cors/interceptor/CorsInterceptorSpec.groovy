package grails3.cors.interceptor

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.springframework.http.HttpHeaders
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(CorsInterceptor)
@Mock(CorsService)
class CorsInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test CORS interceptor matching"() {
        when:"Any request should match the interceptor, not just the 'cors' controller"
            withRequest(controller:"any")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }

    void "Test CORS response headers when method = 'OPTIONS'"() {
        given:
        def origin = 'http://localhost:4200'
        request.addHeader('Origin', origin)
        request.method = 'OPTIONS'

        and:
        interceptor.before()

        expect:
        response.header('Access-Control-Allow-Headers') == 'origin, authorization, accept, content-type, x-requested-with'
        response.header('Access-Control-Allow-Methods') == 'GET, HEAD, POST, PUT, DELETE, TRACE, PATCH, OPTIONS'
        response.header('Access-Control-Max-Age') == '3600'
        response.header('Access-Control-Allow-Origin') == origin
        response.header('Access-Control-Allow-Credentials') == 'true'

    }

    void "Test CORS response headers when method != 'OPTIONS'"() {
        given:
        def origin = 'http://localhost:4200'
        request.addHeader('Origin', origin)

        and:
        interceptor.before()

        expect:
        response.header('Access-Control-Allow-Headers') == null
        response.header('Access-Control-Allow-Methods') == null
        response.header('Access-Control-Max-Age') == null
        response.header('Access-Control-Allow-Origin') == origin
        response.header('Access-Control-Allow-Credentials') == 'true'

    }

    void "Test CORS response headers when method != 'OPTIONS' and Origin = null"() {
        when:
        interceptor.before()

        then:
        response.header('Access-Control-Allow-Headers') == null
        response.header('Access-Control-Allow-Methods') == null
        response.header('Access-Control-Max-Age') == null
        response.header('Access-Control-Allow-Origin') == '*'
        response.header('Access-Control-Allow-Credentials') == 'true'

    }

    void "Test CORS response headers when method = 'OPTIONS' and Origin = null"() {
        given:
        request.method = 'OPTIONS'

        and:
        interceptor.before()

        expect:
        response.header('Access-Control-Allow-Headers') == null
        response.header('Access-Control-Allow-Methods') == null
        response.header('Access-Control-Max-Age') == null
        response.header('Access-Control-Allow-Origin') == '*'
        response.header('Access-Control-Allow-Credentials') == 'true'

    }
}
