# Dropwizard Test

Test implementation of RESTful API with Dropwizard.


## Test

    $ gradle test


## Build

    $ cat credentials.yaml >> configuration.yaml
    $ gradle shadowJar
    $ java -classpath bin/ojdbc6_g.jar:build/distributions/dropwizardtest-0.1.jar \
           edu.oregonstate.mist.dropwizardtest.DropwizardTestApplication \
           server configuration.yaml


## REST Api

### GET

    $ curl --include localhost:8080/api/v1/employee/1
    HTTP/1.1 200 OK
    Date: Tue, 27 Jan 2015 18:17:32 GMT
    Content-Type: application/json
    Vary: Accept-Encoding
    Transfer-Encoding: chunked
    
    {"id":1,"osuId":"123456789","lastName":"Doe","firstName":"John","middleInitial":"A","onidLoginId":"dojo","emailAddress":"dojo@onid.orst.edu","employeeStatus":"A"}

### POST

    $ curl --include --request POST --header "Content-Type: application/json" --data "{\"foo\":\"bar\"}"  --write-out "\n" localhost:8080/api/v1/employee/4
    HTTP/1.1 200 OK
    Date: Tue, 27 Jan 2015 19:35:24 GMT
    Content-Type: application/json
    Transfer-Encoding: chunked
    
    {"foo":"bar"}

### GET with Authentication

No credentials:

    $ curl --include --request GET --write-out "\n" localhost:8080/api/v1/employee/1/OnidLoginId
    HTTP/1.1 401 Unauthorized
    Date: Thu, 29 Jan 2015 21:07:06 GMT
    WWW-Authenticate: Basic realm="DropwizardTestApplication"
    Content-Type: text/plain
    Transfer-Encoding: chunked
    
    Credentials are required to access this resource.

Incorrect credentials:

    $ curl --include --request GET -u username:incorrectpassword --write-out "\n" localhost:8080/api/v1/employee/1/OnidLoginId
    HTTP/1.1 401 Unauthorized
    Date: Thu, 29 Jan 2015 21:07:16 GMT
    WWW-Authenticate: Basic realm="DropwizardTestApplication"
    Content-Type: text/plain
    Transfer-Encoding: chunked
    
    Credentials are required to access this resource.

Correct credentials:

    $ curl --include --request GET -u username:password --write-out "\n" localhost:8080/api/v1/employee/1/OnidLoginId
    HTTP/1.1 200 OK
    Date: Thu, 29 Jan 2015 21:07:21 GMT
    Content-Type: text/plain
    Vary: Accept-Encoding
    Transfer-Encoding: chunked
    
    dojo


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
