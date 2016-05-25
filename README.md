# grails3-cors-interceptor
Add Cross-Origin Resource Sharing (CORS) headers for Grails 3 applications.

# Grails 3+ Only

This plugin will add a new Interceptor (see [Grails 3 Interceptor API](https://grails.github.io/grails-doc/latest/guide/single.html#interceptors)) to your Grails app that adds CORS headers to all your controllers and actions.

This plugin has only been tested with Grails 3.0., 3.0.15 and 3.1.4.

- for Grails 3.1.4+, please use version `1.0.0`
- for Grails 3.0.15+, please use version `0.1.5` 
- for Grails 3.0.4 - 3.0.14, please use version `0.1.2`
 

# Grails 2.x

For Grails 2.x app, please use the execellent [CORS Plugin](https://github.com/davidtinker/grails-cors). In fact, this plugin is based on the Grails 3 servlet filter code provided in the README by that plugin author. The filter code is rewritten as interceptor for this plugin.

# Usage

## 1. Add Plugin Dependency

Add the following dependency to your Grails app,

*build.gradle*

```
compile "org.grails.plugins:grails3-cors-interceptor:0.1.6"
```

## 2. Add HTTP OPTIONS Method URL Mapping 

To support the preflight CORS request with HTTP OPTIONS method, url mappings for OPTIONS method must be added explicitly.

*UrlMappings.groovy*

```
"/books"(resources:'book') // mapping to REST resource "book"
"/books/$id?"(controller:'book', method: 'OPTIONS') // explicitly map OPTIONS method to "book" REST controller
```

## 3. (Optional) Configuration Settings

*application.yml*

```
corsInterceptor:
    includeEnvironments: ['development', 'test']
    excludeEnvironments: ['production']
    allowedOrigins: ['yourhost.com']
```

- includeEnvironments - include this plugin only in the environments listed (default to all environments)
- excludeEnvironments - exclude this plugin from the environments listed (default to null)
- allowedOrigins - white list for allowed origins (default to all origins without restrictions)

# Working with Spring Security Core or Spring Security REST Plugins

See the sample app [grails3-cors-interceptor-spring-security-rest-sample-app](https://github.com/appcela/grails3-cors-interceptor-spring-security-rest-sample-app) for detailed 
instructions on how to get `grails3-cors-interceptor` working with [Spring Security Core](https://grails-plugins.github.io/grails-spring-security-core/v3/index.html) or [Spring Security REST plugin](http://alvarosanchez.github.io/grails-spring-security-rest/2.0.0.M2/docs/index.html).


