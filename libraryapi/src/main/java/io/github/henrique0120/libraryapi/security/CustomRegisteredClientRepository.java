package io.github.henrique0120.libraryapi.security;

import org.jspecify.annotations.Nullable;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

public class CustomRegisteredClientRepository implements RegisteredClientRepository {

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public @Nullable RegisteredClient findById(String id) {
        return null;
    }

    @Override
    public @Nullable RegisteredClient findByClientId(String clientId) {
        return null;
    }
}
