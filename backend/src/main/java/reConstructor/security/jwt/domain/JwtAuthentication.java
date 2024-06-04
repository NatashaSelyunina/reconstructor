package reConstructor.security.jwt.domain;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import reConstructor.domain.entities.Role;

import java.util.Collection;
import java.util.Collections;

public class JwtAuthentication implements Authentication {

    private boolean authenticated;

    private String identifier;

    private Role role;

    private Object restaurantId;

    public JwtAuthentication() {
    }

    public JwtAuthentication(String identifier, Role role) {
        this.identifier = identifier;
        this.role = role;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return identifier;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return null;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Role getRole() {
        return role;
    }

    public Object getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Object restaurantId) {
        this.restaurantId = restaurantId;
    }
}
