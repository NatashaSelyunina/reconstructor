package reConstructor.domain.dto.menuDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DishDto {

    private int id;
    @NotBlank(message = "The name of the dish must not be blank")
    @Size(max = 255, message = "The name of the dish must not contain more than 255 characters")
    private String name;
    private String description;
    private String weight;
    private double price;
    private boolean isActive;
    private String imageUrl;

    public DishDto() {
    }

    public DishDto(int id, String name, String description, String weight, double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.isActive = true;
        this.imageUrl = imageUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
