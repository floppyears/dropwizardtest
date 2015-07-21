package edu.oregonstate.mist.dropwizardtest

import edu.oregonstate.mist.dropwizardtest.auth.*
import edu.oregonstate.mist.dropwizardtest.core.*
import edu.oregonstate.mist.dropwizardtest.db.*
import edu.oregonstate.mist.dropwizardtest.resources.*

import io.dropwizard.Application
import io.dropwizard.auth.AuthFactory
import io.dropwizard.auth.basic.BasicAuthFactory
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.dropwizard.hibernate.HibernateBundle
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.jdbi.DBIFactory
import org.skife.jdbi.v2.DBI

/* application class */
public class DropwizardTestApplication extends Application<DropwizardTestApplicationConfiguration> {

    /* create managed database connection pool
       per http://dropwizard.io/manual/hibernate.html#configuration */
    private final HibernateBundle<DropwizardTestApplicationConfiguration> hibernate =
        new HibernateBundle<DropwizardTestApplicationConfiguration>(Employee) {
            @Override
            public DataSourceFactory getDataSourceFactory(DropwizardTestApplicationConfiguration configuration) {
                return configuration.dataSourceFactory
            }
        }

    @Override
    public void initialize(Bootstrap<DropwizardTestApplicationConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate)
    }

    /* run method */
    @Override
    public void run(DropwizardTestApplicationConfiguration configuration, Environment environment) {

        /* create new Hibernate SessionFactory instance */
        final EmployeeDAO edao = new EmployeeDAO(hibernate.sessionFactory)
        environment.jersey().register(new EmployeeResource(edao))

        /* create managed database connection pool and a new DBI instance
           per http://dropwizard.io/manual/jdbi.html#configuration */
        final DBIFactory factory = new DBIFactory()
        final DBI jdbi = factory.build(environment, configuration.dataSourceFactory, 'jdbi')
        final JobDAO jdao = jdbi.onDemand(JobDAO)
        environment.jersey().register(new JobResource(jdao))

        /* enable HTTP basic authentication
           per http://dropwizard.io/manual/auth.html#basic-authentication */
        environment.jersey().register(AuthFactory.binder(new BasicAuthFactory<String>(new SimpleAuthenticator(), 'DropwizardTestApplication', AuthenticatedUser.class)));
    }

    /* main method */
    public static void main(String[] args) throws Exception {
        new DropwizardTestApplication().run(args)
    }
}
