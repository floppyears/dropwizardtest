package edu.oregonstate.mist.dropwizardtest.health;

import com.codahale.metrics.health.HealthCheck;

import edu.oregonstate.mist.dropwizardtest.DropwizardTestDatabase;

public class DatabaseHealthCheck extends HealthCheck {
    private final DropwizardTestDatabase database;

    public DatabaseHealthCheck(DropwizardTestDatabase database) {
        this.database = database;
    }

    @Override
    protected Result check() throws Exception {
        if (database == null) {
            return Result.unhealthy("Database is null!");
        } else {
            return Result.healthy();
        }
    }
}
