package grails3.cors.interceptor

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CorsService)
@Mock([])
class CorsServiceSpec extends Specification {
    MockHttpServletRequest request
    MockHttpServletResponse response

    def setup() {
        request = new MockHttpServletRequest()
        response = new MockHttpServletResponse()
    }

    def cleanup() {
    }

    def "grailsApplication is not null"() {
        expect:
        grailsApplication != null
    }

    void "Test CORS response headers when method = 'OPTIONS'"() {
        given:
        def origin = 'http://localhost:4200'
        request.addHeader('Origin', origin)
        request.method = 'OPTIONS'
        def isHandled = false

        and:
        isHandled = service.processPreflight(request, response)

        expect:
        response.getHeader('Access-Control-Allow-Headers') == 'origin, authorization, accept, content-type, x-requested-with'
        response.getHeader('Access-Control-Allow-Methods') == 'GET, HEAD, POST, PUT, DELETE, TRACE, PATCH, OPTIONS'
        response.getHeader('Access-Control-Max-Age') == '3600'
        response.getHeader('Access-Control-Allow-Origin') == origin
        response.getHeader('Access-Control-Allow-Credentials') == 'true'
        assert isHandled
    }

    void "Test CORS response headers when method != 'OPTIONS'"() {
        given:
        def origin = 'http://localhost:4200'
        request.addHeader('Origin', origin)
        request.method = 'GET'
        def isHandled = false

        and:
        isHandled = service.processPreflight(request, response)

        expect:
        response.getHeader('Access-Control-Allow-Headers') == null
        response.getHeader('Access-Control-Allow-Methods') == null
        response.getHeader('Access-Control-Max-Age') == null
        response.getHeader('Access-Control-Allow-Origin') == origin
        response.getHeader('Access-Control-Allow-Credentials') == 'true'
        assert !isHandled
    }
}
