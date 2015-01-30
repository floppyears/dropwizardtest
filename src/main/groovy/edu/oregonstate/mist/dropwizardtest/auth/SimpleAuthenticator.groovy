package edu.oregonstate.mist.dropwizardtest.auth;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class SimpleAuthenticator implements Authenticator<BasicCredentials, AuthenticatedUser> {
    public Optional<AuthenticatedUser> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if (valid(credentials)) {
            return Optional.of(new AuthenticatedUser(credentials.getUsername()));
        } else {
            return Optional.absent();
        }
    }

    private Boolean valid(BasicCredentials credentials) {
        return (credentials.getPassword().equals("password") && credentials.getUsername().equals("username"));
    }
}
