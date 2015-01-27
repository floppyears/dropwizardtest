# Dropwizard Test

Test implementation of RESTful API with Dropwizard.


## Build

    $ mvn package
    $ java -jar target/dropwizardtest-0.0.1.jar server dropwizardtest.yaml


## REST Api

### GET

    $ curl --include localhost:8080/api/v1/employee/1
    HTTP/1.1 200 OK
    Date: Tue, 27 Jan 2015 18:17:32 GMT
    Content-Type: application/json
    Vary: Accept-Encoding
    Transfer-Encoding: chunked
    
    {"id":1,"osuId":"123456789","lastName":"Doe","firstName":"John","middleInitial":"A","onidLoginId":"dojo","emailAddress":"dojo@onid.orst.edu","employeeStatus":"A"}


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
