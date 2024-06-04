package reConstructor.domain.dto.menuDto;

import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuSectionDto {

    private int id;
    private int parentSectionId;
    @NotBlank(message = "The section name cannot be empty")
    private String name;
    private boolean isActive;
    private List<MenuSectionDto> sections;
    private Set<DishDto> dishes;

    public MenuSectionDto() {
        this.sections = new ArrayList<>();
    }

    public MenuSectionDto(int id, int parentSectionId, String name) {
        this.id = id;
        this.parentSectionId = parentSectionId;
        this.name = name;
        this.isActive = true;
        this.sections = new ArrayList<>();
    }

    public MenuSectionDto(int id, int parentSectionId, String name, List<MenuSectionDto> sections,
                          HashSet<DishDto> dishes) {
        this.id = id;
        this.parentSectionId = parentSectionId;
        this.name = name;
        this.isActive = true;
        this.sections = sections;
        this.dishes = dishes;
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

    public int getParentSectionId() {
        return parentSectionId;
    }

    public void setParentSectionId(int parentSectionId) {
        this.parentSectionId = parentSectionId;
    }

    public Set<DishDto> getDishes() {
        return dishes;
    }

    public void setDishes(Set<DishDto> dishes) {
        this.dishes = dishes;
    }

    public List<MenuSectionDto> getSections() {
        return sections;
    }

    public void setSections(List<MenuSectionDto> sections) {
        this.sections = sections;
    }
}
