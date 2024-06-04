package reConstructor.domain.interfaces;

import reConstructor.domain.entities.Check;
import reConstructor.domain.entities.Restaurant;

import java.util.List;

public interface ITable {

    int getId();

    void setId(int id);

    String getNumber();

    void setNumber(String number);

    boolean isFree();

    void setFree(boolean isFree);

    List<Check> getChecks();

    void setChecks(List<Check> checks);

    boolean isActive();

    void setActive(boolean isActive);

    Restaurant getRestaurant();

    void setRestaurant(Restaurant restaurant);
}
