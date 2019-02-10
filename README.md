# simple-xacml-sample

Simple Spring Boot application demonstrating the use of the [XACML 3.0 JSON Profile]( http://docs.oasis-open.org/xacml/xacml-json-http/v1.0/xacml-json-http-v1.0.html) in a Policy Enforcement Point (PEP) in a REST API to evaluate access to a given resource.

The application contains a sample RestController(`SecuredSampleController`) which uses the XACML 3.0 JSON Profile against a Policy Decision Point (PDP) to enforce access control. It will return a simple String representing the resource if access should be permitted, and *403 Forbidden* otherwise. To be able to demonstrate the PEP example standalone, a Mock PDP (Policy Decision Point) is also included.

## How to test the sample

### Run

Runs as a standard Spring Boot app, see [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html) for details.

### Use

Point your browser to http://localhost:8080/swagger-ui.html to experiment using swagger. 

The sample API requires `Basic Authentication`, however it accepts all username/password combinations for demostration purposes. In order to get a successful response (i.e. where access is permitted) from the API when using the Mock PDP, you must authenticate with the username: **success** - all other usernames will give a "Deny" and 403 Forbidden. 



