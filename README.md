# grails3-cors-interceptor
Add Cross-Origin Resource Sharing (CORS) headers for Grails 3 applications.

# Grails 3+ Only

This plugin will add a new Interceptor (see [Grails 3 Interceptor API](https://grails.github.io/grails-doc/latest/guide/single.html#interceptors)) to your Grails app that adds CORS headers to all your controllers and actions.

This plugin has only been tested with Grails 3.0.4.

# Grails 2.x

For Grails 2.x app, please use the execellent [CORS Plugin](https://github.com/davidtinker/grails-cors). In fact, this plugin is based on the Grails 3 servlet filter code provided in the README by that plugin author. The filter code is rewritten as interceptor for this plugin.

# Usage

## 1. Add Plugin Dependency

Add the following dependency to your Grails app,

*build.gradle*

```
compile "org.grails.plugins:grails3-cors-interceptor:0.1.1"
```

## 2. Add HTTP OPTIONS Method URL Mapping 

To support the preflight CORS request with HTTP OPTIONS method, url mappings for OPTIONS method must be added explicitly.

*UrlMappings.groovy*

```
"/books"(resources:'book') // mapping to REST resource "book"
"/books/$id?"(controller:'book', method: 'OPTIONS') // explicitly map OPTIONS method to "book" REST controller
```



