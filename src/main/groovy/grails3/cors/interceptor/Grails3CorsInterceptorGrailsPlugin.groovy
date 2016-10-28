package grails3.cors.interceptor

import grails.plugins.*

class Grails3CorsInterceptorGrailsPlugin extends Plugin {

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.1.4 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/*.gsp"
    ]

    // TODO Fill in these fields
    def version = "1.2.1" // must be added explicitly
    def title = "Grails3 CORS Interceptor" // Headline display name of the plugin
    def author = "Ivan Chang"
    def authorEmail = "ichang@appcela.com"
    def description = '''\
Add Cross-Origin Resource Sharing (CORS) headers for Grails applications.
'''
    def profiles = ['web']

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/grails3-cors-interceptor"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "MIT"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "Appcela", url: "http://www.appcela.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

    Closure doWithSpring() { {->
            // TODO Implement runtime spring config (optional)
        }
    }

    void doWithDynamicMethods() {
        // TODO Implement registering dynamic methods to classes (optional)
    }

    void doWithApplicationContext() {
        // Delegate OPTIONS requests to controllers
        applicationContext.dispatcherServlet.setDispatchOptionsRequest(true)
    }

    void onChange(Map<String, Object> event) {
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    void onConfigChange(Map<String, Object> event) {
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    void onShutdown(Map<String, Object> event) {
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
