package grails3.cors.interceptor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.filter.GenericFilterBean

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

class SpringSecurityCorsFilter extends GenericFilterBean {
    @Autowired
    CorsService corsService

    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if( !corsService.processPreflight(request, response) ) {
            chain.doFilter(request, response)
        }
    }
}
