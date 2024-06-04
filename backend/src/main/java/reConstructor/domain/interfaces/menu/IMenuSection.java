package reConstructor.domain.interfaces.menu;

import reConstructor.domain.entities.menu.Dish;

import java.util.Set;

public interface IMenuSection {

    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    Set<Dish> getDishes();

    void setDishes(Set<Dish> dishes);

    boolean isActive();

    void setActive(boolean isActive);

    int getParentSectionId();

    void setParentSectionId(int parentSectionId);
}
