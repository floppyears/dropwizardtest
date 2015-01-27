# Dropwizard Test

Test implementation of RESTful API with Dropwizard.

    $ mvn package
    $ java -jar target/dropwizardtest-0.0.1.jar server dropwizardtest.json
    $ curl --include localhost:8080/api/v1/employee/1
    