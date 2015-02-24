package edu.oregonstate.mist.dropwizardtest.test

import edu.oregonstate.mist.dropwizardtest.DropwizardTestApplication
import edu.oregonstate.mist.dropwizardtest.DropwizardTestApplicationConfiguration

import com.sun.jersey.api.client.Client

import org.junit.Test
import org.junit.ClassRule
import static org.junit.Assert.assertThat

import io.dropwizard.testing.junit.DropwizardAppRule

public class EmployeeTest {
    @ClassRule
    public static final DropwizardAppRule<DropwizardTestApplicationConfiguration> RULE =
            new DropwizardAppRule<DropwizardTestApplicationConfiguration>(DropwizardTestApplication, 'configuration.yaml')

    @Test
    public void testOptions() {
        Client client = new Client()

        List o = client.target('http://localhost:8080/api/v1/employee')
                       .request('application/json')
                       .options(List)

        assertThat(o != ['OPTIONS','PUT'])
        assertThat(1 == 2)
    }
}
