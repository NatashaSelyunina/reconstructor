package reConstructor.domain.entities;

import jakarta.persistence.*;
import reConstructor.domain.interfaces.ITable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tables")
public class Tables implements ITable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private String number;

    @Column(name = "is_free")
    private boolean isFree;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "restTable", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Check> checks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = true)
    private Restaurant restaurant;

    @Column(name = "qr_code_url")
    private String qrCodeImageUrl;

    public Tables() {
        this.isActive = true;
    }

    public Tables(int id, String number) {
        this.id = id;
        this.number = number;
        this.isActive = true;
        this.isFree = true;
    }

    public Tables(int id, String number, List<Check> checks) {
        this.id = id;
        this.number = number;
        this.isFree = true;
        this.checks = checks;
        this.isActive = true;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean isFree() {
        return isFree;
    }

    @Override
    public void setFree(boolean free) {
        isFree = free;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public List<Check> getChecks() {
        return new ArrayList<>(checks);
    }

    @Override
    public void setChecks(List<Check> checks) {
        this.checks = checks;
    }

    @Override
    public Restaurant getRestaurant() {
        return restaurant;
    }

    @Override
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getQrCodeImageUrl() {
        return qrCodeImageUrl;
    }

    public void setQrCodeImageUrl(String qrCodeImageUrl) {
        this.qrCodeImageUrl = qrCodeImageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tables tables = (Tables) o;
        return id == tables.id && isFree == tables.isFree && isActive == tables.isActive
                && Objects.equals(number, tables.number) && Objects.equals(checks, tables.checks)
                && Objects.equals(restaurant, tables.restaurant)
                && Objects.equals(qrCodeImageUrl, tables.qrCodeImageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, isFree, isActive, checks, restaurant, qrCodeImageUrl);
    }

    @Override
    public String toString() {
        return "Tables{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", isFree=" + isFree +
                ", isActive=" + isActive +
                ", restaurant=" + restaurant +
                ", qrCodeImageUrl='" + qrCodeImageUrl + '\'' +
                '}';
    }
}
