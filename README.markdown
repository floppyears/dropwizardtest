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

The following HTTP requests were performed with telnet:

    $ telnet localhost 8080
    Trying ::1...
    telnet: connect to address ::1: Connection refused
    Trying 127.0.0.1...
    Connected to localhost.
    Escape character is '^]'.
    

### OPTIONS

/api/v1/employee:

    OPTIONS /api/v1/employee HTTP/1.1
    Host: localhost:8080
    
    HTTP/1.1 200 OK
    Date: Wed, 11 Feb 2015 23:32:07 GMT
    Content-Type: application/json
    Transfer-Encoding: chunked
    
    11
    ["OPTIONS","PUT"]
    0

/api/v1/employee/1:

    OPTIONS /api/v1/employee/1 HTTP/1.1
    Host: localhost:8080
    
    HTTP/1.1 200 OK
    Date: Wed, 11 Feb 2015 23:32:30 GMT
    Content-Type: application/json
    Transfer-Encoding: chunked
    
    11
    ["OPTIONS","GET"]
    0

/api/v1/employee/1/OnidLoginId:

    OPTIONS /api/v1/employee/1/OnidLoginId HTTP/1.1
    Host: localhost:8080
    
    HTTP/1.1 200 OK
    Date: Wed, 11 Feb 2015 23:32:57 GMT
    Content-Type: application/json
    Transfer-Encoding: chunked
    
    11
    ["OPTIONS","GET"]
    0

/api/v1/job/1:

    OPTIONS /api/v1/job/1 HTTP/1.1
    Host: localhost:8080
    
    HTTP/1.1 200 OK
    Date: Wed, 18 Feb 2015 22:57:19 GMT
    Content-Type: application/json
    Transfer-Encoding: chunked
    
    11
    ["OPTIONS","GET"]
    0

### GET

Employee exists:

    GET /api/v1/employee/25 HTTP/1.1
    Host: localhost:8080
    
    HTTP/1.1 200 OK
    Date: Wed, 11 Feb 2015 23:37:36 GMT
    Content-Type: application/json
    Vary: Accept-Encoding
    Transfer-Encoding: chunked
    
    C1
    {"id":25,"osuId":"830226005","lastName":"Mustard HR-OSCAR","firstName":"Colonel","middleInitial":null,"onidLoginId":"whiteja","emailAddress":"cedenoj@onid.oregonstate.edu","employeeStatus":"A"}
    0

Employee does not exist:

    GET /api/v1/employee/101 HTTP/1.1
    Host: localhost:8080
    
    HTTP/1.1 404 Not Found
    Date: Wed, 11 Feb 2015 23:38:57 GMT
    Content-Length: 0

Jobs exist:

    GET /api/v1/job/162 HTTP/1.1
    Host: localhost:8080
    
    HTTP/1.1 200 OK
    Date: Wed, 18 Feb 2015 22:58:06 GMT
    Content-Type: application/json
    Vary: Accept-Encoding
    Transfer-Encoding: chunked
    
    5AB
    [{"id":162,"positionNumber":"C10210","suffix":"00","status":"T","jobTitle":"Professor","eclsCode":"UA","appointmentType":"Ranked Faculty","beginDate":"1998-07-01","endDate":"2003-06-30","pclsCode":"UC202","salGrade":"S","salStep":0,"orgnCodeTs":"252100","orgnDesc":"SPH - Physics","bctrTitle":"ASBC","supervisorPidm":378,"supervisorPosn":"C10214","supervisorSuff":"00","trialInd":0,"annualInd":0,"evalDate":null,"low":0,"midpoint":0,"high":0,"salary":6661,"sgrpCode":"19978A"},{"id":162,"positionNumber":"C25213","suffix":"00","status":"T","jobTitle":"Academic Wage Appt-Salaried","eclsCode":"UV","appointmentType":"Academic Wage","beginDate":"2003-09-16","endDate":"2010-06-15","pclsCode":"UV901","salGrade":"S","salStep":0,"orgnCodeTs":"252100","orgnDesc":"SPH - Physics","bctrTitle":"ASBC","supervisorPidm":0,"supervisorPosn":null,"supervisorSuff":"00","trialInd":0,"annualInd":0,"evalDate":null,"low":0,"midpoint":0,"high":0,"salary":6256,"sgrpCode":"20034B"},{"id":162,"positionNumber":"C28213","suffix":"00","status":"T","jobTitle":"Summer Session/Non-Teaching","eclsCode":"UV","appointmentType":"Academic Wage","beginDate":"2000-07-01","endDate":"2001-07-31","pclsCode":"UV351","salGrade":"S","salStep":0,"orgnCodeTs":"252100","orgnDesc":"SPH - Physics","bctrTitle":"ASBC","supervisorPidm":0,"supervisorPosn":null,"supervisorSuff":"00","trialInd":0,"annualInd":0,"evalDate":null,"low":0,"midpoint":0,"high":0,"salary":6530,"sgrpCode":"19989B"}]
    0

Job does not exist:

    GET /api/v1/job/101 HTTP/1.1
    Host: localhost:8080
    
    HTTP/1.1 404 Not Found
    Date: Wed, 18 Feb 2015 23:00:03 GMT
    Content-Length: 0

### PUT

Create or update employee with specified id:

    PUT /api/v1/employee HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json
    Content-Length: 165
    
    {"id":111,"osuId":"123571113","lastName":"Bar","middleInitial":"W","firstName":"Foo","onidLoginId":"foobar","emailAddress":"foobar@example.com","employeeStatus":"A"}
    HTTP/1.1 200 OK
    Date: Thu, 12 Feb 2015 00:04:12 GMT
    Content-Type: application/json
    Transfer-Encoding: chunked
    
    A5
    {"id":111,"osuId":"123571113","lastName":"Bar","firstName":"Foo","middleInitial":"W","onidLoginId":"foobar","emailAddress":"foobar@example.com","employeeStatus":"A"}
    0

Invalid data:

    PUT /api/v1/employee HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json
    Content-Length: 155
    
    {"id":111,"osuId":"","lastName":"","middleInitial":"T","firstName":"Foo","onidLoginId":"foobar","emailAddress":"foobaratexampledotnet","employeeStatus":""}
    HTTP/1.1 422
    Date: Thu, 12 Feb 2015 00:01:50 GMT
    Content-Type: application/json
    Transfer-Encoding: chunked
    
    C3
    {"errors":["emailAddress not a well-formed email address (was foobaratexampledotnet)","employeeStatus may not be empty (was )","lastName may not be empty (was )","osuId may not be empty (was )"]}
    0

### GET with Authentication

User is authenticated:

    GET /api/v1/employee/25/OnidLoginId HTTP/1.1
    Host: localhost:8080
    Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=
    
    HTTP/1.1 200 OK
    Date: Wed, 11 Feb 2015 23:49:56 GMT
    Content-Type: text/plain
    Vary: Accept-Encoding
    Transfer-Encoding: chunked
    
    7
    whiteja
    0

User is not authenticated:

    GET /api/v1/employee/25/OnidLoginId HTTP/1.1
    Host: localhost:8080
    Authorization: dXNlcm5hbWU6aW5jb3JyZWN0cGFzc3dvcmQ=
    
    HTTP/1.1 401 Unauthorized
    Date: Wed, 11 Feb 2015 23:52:27 GMT
    WWW-Authenticate: Basic realm="DropwizardTestApplication"
    Content-Type: text/plain
    Transfer-Encoding: chunked
    
    31
    Credentials are required to access this resource.
    0

## Tasks

Trigger JVM garbage collection:

    POST /tasks/gc HTTP/1.1
    Host: localhost:8081
    
    HTTP/1.1 200 OK
    Date: Wed, 11 Feb 2015 23:42:55 GMT
    Content-Type: text/plain; charset=UTF-8
    Transfer-Encoding: chunked
    
    E
    Running GC...
    
    6
    Done!
    
    0

## Operational Menu

### Metrics

    GET /metrics?pretty=true HTTP/1.1
    Host: localhost:8081
    
    HTTP/1.1 200 OK
    Date: Thu, 12 Feb 2015 00:08:34 GMT
    Content-Type: application/json
    Cache-Control: must-revalidate,no-cache,no-store
    Transfer-Encoding: chunked
    
    63
    {
      "version" : "3.0.0",
      "gauges" : {
        "jvm.buffers.direct.capacity" : {
          "value" : 66004

...

    3F
    
        },
        "jvm.memory.heap.used" : {
          "value" : 56126296

...

    36
    
        },
        "jvm.threads.count" : {
          "value" : 27

...

    209E
    ,
      "timers" : {
        "edu.oregonstate.mist.dropwizardtest.resources.EmployeeResource.getById" : {
          "count" : 3,
          "max" : 0.24506100000000003,
          "mean" : 0.08771366666666668,
          "min" : 0.00263,
          "p50" : 0.01545,
          "p75" : 0.24506100000000003,
          "p95" : 0.24506100000000003,
          "p98" : 0.24506100000000003,
          "p99" : 0.24506100000000003,
          "p999" : 0.24506100000000003,
          "stddev" : 0.1364174680176015,
          "m15_rate" : 0.0011502901446789392,
          "m1_rate" : 3.1834063780301826E-4,
          "m5_rate" : 0.0015263061126552307,
          "mean_rate" : 0.001327797565293289,
          "duration_units" : "seconds",
          "rate_units" : "calls/second"
        },

...
    
### Ping

    GET /ping HTTP/1.1
    Host: localhost:8081
    
    HTTP/1.1 200 OK
    Date: Wed, 11 Feb 2015 23:44:20 GMT
    Cache-Control: must-revalidate,no-cache,no-store
    Content-Type: text/plain; charset=ISO-8859-1
    Content-Length: 5
    
    pong

### Threads

    GET /threads HTTP/1.1
    Host: localhost:8081
    
    HTTP/1.1 200 OK
    Date: Thu, 12 Feb 2015 00:16:42 GMT
    Content-Type: text/plain
    Cache-Control: must-revalidate,no-cache,no-store
    Transfer-Encoding: chunked
    
    53A4
    Reference Handler id=2 state=WAITING
        - waiting on <0x05cf7ac3> (a java.lang.ref.Reference$Lock)
        - locked <0x05cf7ac3> (a java.lang.ref.Reference$Lock)
        at java.lang.Object.wait(Native Method)
        at java.lang.Object.wait(Object.java:503)
        at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:133)
    
    Finalizer id=3 state=WAITING
        - waiting on <0x09c96812> (a java.lang.ref.ReferenceQueue$Lock)
        - locked <0x09c96812> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.Object.wait(Native Method)
        at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:135)
        at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:151)
        at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:189)
    
    Signal Dispatcher id=4 state=RUNNABLE
    
    async-console-appender-1 id=9 state=TIMED_WAITING
        - waiting on <0x35a594c2> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        - locked <0x35a594c2> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)

...

    dw-admin-64 id=64 state=RUNNABLE
        at sun.management.ThreadImpl.dumpThreads0(Native Method)
        at sun.management.ThreadImpl.dumpAllThreads(ThreadImpl.java:446)
        at com.codahale.metrics.jvm.ThreadDump.dump(ThreadDump.java:30)
        at com.codahale.metrics.servlets.ThreadDumpServlet.doGet(ThreadDumpServlet.java:36)
        at javax.servlet.http.HttpServlet.service(HttpServlet.java:735)
        at javax.servlet.http.HttpServlet.service(HttpServlet.java:848)
        at com.codahale.metrics.servlets.AdminServlet.service(AdminServlet.java:100)
        at javax.servlet.http.HttpServlet.service(HttpServlet.java:848)
        at io.dropwizard.jetty.NonblockingServletHolder.handle(NonblockingServletHolder.java:49)
        at org.eclipse.jetty.servlet.ServletHandler$CachedChain.doFilter(ServletHandler.java:1515)
        at io.dropwizard.jersey.filter.AllowedMethodsFilter.handle(AllowedMethodsFilter.java:44)
        at io.dropwizard.jersey.filter.AllowedMethodsFilter.doFilter(AllowedMethodsFilter.java:39)
        at org.eclipse.jetty.servlet.ServletHandler$CachedChain.doFilter(ServletHandler.java:1486)
        at org.eclipse.jetty.servlet.ServletHandler.doHandle(ServletHandler.java:519)
        at org.eclipse.jetty.server.handler.ContextHandler.doHandle(ContextHandler.java:1097)
        at org.eclipse.jetty.servlet.ServletHandler.doScope(ServletHandler.java:448)
        at org.eclipse.jetty.server.handler.ContextHandler.doScope(ContextHandler.java:1031)
        at org.eclipse.jetty.server.handler.ScopedHandler.handle(ScopedHandler.java:136)
        at io.dropwizard.jetty.RoutingHandler.handle(RoutingHandler.java:51)
        at org.eclipse.jetty.server.handler.HandlerWrapper.handle(HandlerWrapper.java:97)
        at org.eclipse.jetty.server.handler.RequestLogHandler.handle(RequestLogHandler.java:92)
        at org.eclipse.jetty.server.handler.HandlerWrapper.handle(HandlerWrapper.java:97)
        at org.eclipse.jetty.server.handler.StatisticsHandler.handle(StatisticsHandler.java:162)
        at org.eclipse.jetty.server.handler.HandlerWrapper.handle(HandlerWrapper.java:97)
        at org.eclipse.jetty.server.Server.handle(Server.java:446)
        at org.eclipse.jetty.server.HttpChannel.handle(HttpChannel.java:271)
        at org.eclipse.jetty.server.HttpConnection.onFillable(HttpConnection.java:246)
        at org.eclipse.jetty.io.AbstractConnection$ReadCallback.run(AbstractConnection.java:358)
        at org.eclipse.jetty.util.thread.QueuedThreadPool.runJob(QueuedThreadPool.java:601)
        at org.eclipse.jetty.util.thread.QueuedThreadPool$3.run(QueuedThreadPool.java:532)
        at java.lang.Thread.run(Thread.java:744)
    
    
    
    0

### Health Check

    GET /healthcheck HTTP/1.1
    Host: localhost:8081
    
    HTTP/1.1 200 OK
    Date: Thu, 19 Feb 2015 17:37:39 GMT
    Content-Type: application/json
    Cache-Control: must-revalidate,no-cache,no-store
    Content-Length: 83
    
    {"deadlocks":{"healthy":true},"hibernate":{"healthy":true},"jdbi":{"healthy":true}}

## Logs

    $ tail logs/dropwizardtest.log | grep ^INFO
