package reConstructor.domain.entities;

import jakarta.persistence.*;
import reConstructor.domain.entities.menu.Dish;

import java.util.Objects;

@Entity
@Table(name = "check_dish")
public class CheckDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "check_id")
    private Check check;
    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;
    @Column(name = "dish_count")
    private int dishCount;
    @Column(name = "item_price")
    private double itemPrice;

    public CheckDetails() {
    }

    public CheckDetails(long id, Check check, Dish dish, int dishCount, double itemPrice) {
        this.id = id;
        this.check = check;
        this.dish = dish;
        this.dishCount = dishCount;
        this.itemPrice = itemPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckDetails that = (CheckDetails) o;
        return id == that.id && dishCount == that.dishCount && Double.compare(itemPrice, that.itemPrice) == 0
                && Objects.equals(check, that.check) && Objects.equals(dish, that.dish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, check, dish, dishCount, itemPrice);
    }

    @Override
    public String toString() {
        return "CheckDetails{" +
                "id=" + id +
                ", check=" + check +
                ", dish=" + dish +
                ", dishCount=" + dishCount +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
