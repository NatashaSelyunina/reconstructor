package reConstructor.domain.interfaces;

import reConstructor.domain.entities.Restaurant;
import reConstructor.domain.entities.Role;

import java.util.Set;

public interface IModerator {

    long getId();

    void setId(long id);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    Role getRole();

    void setRole(Role role);

    Boolean isActive();

    void setActive(Boolean active);

    Set<Restaurant> getRestaurants();

    void setRestaurants(Set<Restaurant> restaurants);
}
