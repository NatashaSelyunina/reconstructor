package reConstructor.domain.entities.menu;

import jakarta.persistence.*;
import reConstructor.domain.entities.Restaurant;
import reConstructor.domain.interfaces.menu.IMenuSection;

import java.util.*;

@Entity
@Table(name = "menu_sections")
public class MenuSection implements IMenuSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "parent_section_id")
    private int parentSectionId;

    @OneToMany(mappedBy = "parentSectionId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuSection> subSections;

    @Column(name = "name")
    private String name;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToMany
    @JoinTable(
            name = "dish_section",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private Set<Dish> dishes;

    public MenuSection() {
        this.subSections = new ArrayList<>();
    }

    public MenuSection(int parentSectionId, String name, Restaurant restaurant) {
        this.parentSectionId = parentSectionId;
        this.name = name;
        this.restaurant = restaurant;
        this.isActive = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public int getParentSectionId() {
        return parentSectionId;
    }

    public void setParentSectionId(int parentSectionId) {
        this.parentSectionId = parentSectionId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<MenuSection> getSubSections() {
        return subSections;
    }

    public void setSubSections(List<MenuSection> subSections) {
        this.subSections = subSections;
    }
}
