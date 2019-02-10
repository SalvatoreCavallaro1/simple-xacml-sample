# simple-xacml-sample

Simple REST API in a Spring Boot application demonstrating the use of the [XACML 3.0 JSON Profile]( http://docs.oasis-open.org/xacml/xacml-json-http/v1.0/xacml-json-http-v1.0.html) in a Policy Enforcement Point (PEP) to evaluate access to a given resource.

The application contains a Sample RestController which uses the XACML 3.0 JSON Profile against a Policy Decision Point (PDP) to enforce access control - `SecuredSampleController` - returning a simple String if access should be permitted, and `403 Forbidden` otherwise. To be able to demonstrate the PEP example standalone, a Mock PDP (Policy Decision Point) is also included.

## How to test the sample

Runs as a standard Spring Boot app, see https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html for details.

Point your browser to http://localhost:8080/swagger-ui.html to experiment using swagger. 

The sample API requires `Basic Authentication`, however it accepts all username/password combinations for demostration purposes. In order to get a successful response (i.e. where access is permitted) from the API when using the Mock PDP, you must authenticate with the username: **success** - all other usernames will give a "Deny" and 403 Forbidden. 



