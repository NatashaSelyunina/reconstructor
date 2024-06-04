package reConstructor.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import reConstructor.domain.dto.menuDto.DishDto;

public class CheckDetailsDto {

    private long id;
    @NotNull
    private DishDto dishDto;
    @Min(1)
    private int dishCount;
    private double itemPrice;

    public CheckDetailsDto(long id, int dishCount, DishDto dishDto, double itemPrice) {
        this.id = id;
        this.dishDto = dishDto;
        this.dishCount = dishCount;
        this.itemPrice = itemPrice;
    }

    public CheckDetailsDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DishDto getDishDto() {
        return dishDto;
    }

    public void setDishDto(DishDto dishDto) {
        this.dishDto = dishDto;
    }

    public int getDishCount() {
        return dishCount;
    }

    public void setDishCount(int dishCount) {
        this.dishCount = dishCount;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
