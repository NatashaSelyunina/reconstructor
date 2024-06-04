package reConstructor.domain.interfaces;

import reConstructor.domain.entities.Restaurant;
import reConstructor.domain.entities.Role;

public interface IStaff {

    long getId();

    void setId(long id);

    String getName();

    void setName(String name);

    String getSurname();

    void setSurname(String surname);

    String getDateOfBirth();

    void setDateOfBirth(String dateOfBirth);

    String getCode();

    void setCode(String code);

    String getPassword();

    void setPassword(String password);

    Role getRole();

    void setRole(Role roles);

    boolean isWorking();

    void setWorking(boolean working);

    boolean isActive();

    void setActive(boolean isActive);

    Restaurant getRestaurant();

    void setRestaurant(Restaurant restaurant);
}
