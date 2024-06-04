package reConstructor.domain.entities.menu;

import jakarta.persistence.*;
import reConstructor.domain.interfaces.menu.IDish;

@Entity
@Table(name = "dishes")
public class Dish implements IDish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "weight")
    private String weight;

    @Column(name = "price")
    private double price;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "image_url")
    private String imageUrl;

    public Dish() {
        this.isActive = true;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getWeight() {
        return weight;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String photo) {
        this.imageUrl = photo;
    }
}
