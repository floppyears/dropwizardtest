package edu.oregonstate.mist.dropwizardtest;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import edu.oregonstate.mist.dropwizardtest.resources.EmployeeResource;
import edu.oregonstate.mist.dropwizardtest.health.*;

public class DropwizardTestApplication extends Application<DropwizardTestApplicationConfiguration> {

    public static DropwizardTestDatabase database = new DropwizardTestDatabase();

    public static void main(String[] args) throws Exception {
        new DropwizardTestApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<DropwizardTestApplicationConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DropwizardTestApplicationConfiguration configuration, Environment environment) {
        final EmployeeResource resource = new EmployeeResource();
        environment.healthChecks().register("database", new DatabaseHealthCheck(database));
        environment.jersey().register(resource);
    }
}
