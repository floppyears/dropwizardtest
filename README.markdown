# Dropwizard Test

Test implementation of RESTful API with Dropwizard.


## Test

    $ gradle test


## Build

Define database connection and add it to configuration:

    $ cat credentials.yaml >> configuration.yaml

Compile application into single Java archive:

    $ gradle shadowJar

Run application:

    $ java -classpath bin/ojdbc6_g.jar:build/distributions/dropwizardtest-0.1.jar \
           edu.oregonstate.mist.dropwizardtest.DropwizardTestApplication \
           server configuration.yaml


## REST Api

### GET

Employee exists:

    $ curl --include \
           --request GET \
           --write-out "\n" \
           localhost:8080/api/v1/employee/25
    HTTP/1.1 200 OK
    Date: Thu, 05 Feb 2015 18:38:57 GMT
    Content-Type: application/json
    Vary: Accept-Encoding
    Transfer-Encoding: chunked
    
    {"id":25,"osuId":"830226005","lastName":"Mustard HR-OSCAR","firstName":"Colonel","middleInitial":null,"onidLoginId":"whiteja","emailAddress":"cedenoj@onid.oregonstate.edu","employeeStatus":"A"}

Employee does not exist:

    $ curl --include \
           --request GET \
           --write-out "\n" \
           localhost:8080/api/v1/employee/101
    HTTP/1.1 404 Not Found
    Date: Thu, 05 Feb 2015 18:39:05 GMT
    Content-Length: 0

### POST

Create or update employee with specified id:

    $ curl --include \
           --request POST \
           --header "Content-Type: application/json" \
           --data '{"id":111,"osuId":"123571113","lastName":"Bar","middleInitial":"W","firstName":"Foo","onidLoginId":"foobar","emailAddress":"foobar@example.com","employeeStatus":"A"}' \
           --write-out "\n" \
           localhost:8080/api/v1/employee
    HTTP/1.1 200 OK
    Date: Thu, 05 Feb 2015 22:24:27 GMT
    Content-Type: application/json
    Transfer-Encoding: chunked
    
    {"id":111,"osuId":"123571113","lastName":"Bar","firstName":"Foo","middleInitial":"W","onidLoginId":"foobar","emailAddress":"foobar@example.com","employeeStatus":"A"}

Invalid data:

    $ curl --include \
           --request POST \
           --header "Content-Type: application/json" \
           --data '{"id":111,"osuId":"","lastName":"","middleInitial":"T","firstName":"Foo","onidLoginId":"foobar","emailAddress":"foobaratexampledotnet","employeeStatus":""}' \
           --write-out "\n" \
           localhost:8080/api/v1/employee
    HTTP/1.1 422
    Date: Tue, 10 Feb 2015 00:42:19 GMT
    Content-Type: application/json
    Transfer-Encoding: chunked
    
    {"errors":["emailAddress not a well-formed email address (was foobaratexampledotnet)","employeeStatus may not be empty (was )","lastName may not be empty (was )","osuId may not be empty (was )"]}


### GET with Authentication

User is authenticated:

    $ curl --include \
           --request GET \
           --user username:password \
           --write-out "\n" \
           localhost:8080/api/v1/employee/25/OnidLoginId
    HTTP/1.1 200 OK
    Date: Thu, 05 Feb 2015 18:49:28 GMT
    Content-Type: text/plain
    Vary: Accept-Encoding
    Transfer-Encoding: chunked
    
    whiteja

User is not authenticated:

    $ curl --include \
           --request GET \
           --user username:incorrectpassword \
           --write-out "\n" \
           localhost:8080/api/v1/employee/25/OnidLoginId
    HTTP/1.1 401 Unauthorized
    Date: Thu, 05 Feb 2015 18:49:51 GMT
    WWW-Authenticate: Basic realm="DropwizardTestApplication"
    Content-Type: text/plain
    Transfer-Encoding: chunked
    
    Credentials are required to access this resource.


## Tasks

Trigger JVM garbage collection:

    $ curl --request POST localhost:8081/tasks/gc
    Running GC...
    Done!


## Operational Menu

### Metrics

    $ curl --include localhost:8081/metrics?pretty=true
    
### Ping

    $ curl --include localhost:8081/ping
    HTTP/1.1 200 OK
    Date: Tue, 27 Jan 2015 18:14:53 GMT
    Cache-Control: must-revalidate,no-cache,no-store
    Content-Type: text/plain; charset=ISO-8859-1
    Content-Length: 5
    
    pong

### Threads

    $ curl --include http://localhost:8081/threads

### Health Check

    $ curl --include localhost:8081/healthcheck
    HTTP/1.1 200 OK
    Date: Tue, 27 Jan 2015 18:15:38 GMT
    Content-Type: application/json
    Cache-Control: must-revalidate,no-cache,no-store
    Content-Length: 58
    
    {"database":{"healthy":true},"deadlocks":{"healthy":true}}

## Logs

    $ tail logs/dropwizardtest.log | grep ^INFO
