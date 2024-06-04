package reConstructor.domain.interfaces.menu;

public interface IDish {

    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    String getWeight();

    void setWeight(String weight);

    double getPrice();

    void setPrice(double price);

    boolean isActive();

    void setActive(boolean isActive);
}
