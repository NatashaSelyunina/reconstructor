package reConstructor.domain.entities;

import jakarta.persistence.*;

import java.util.Set;

import reConstructor.domain.entities.menu.MenuSection;
import reConstructor.domain.interfaces.IRestaurant;

@Entity
@Table(name = "restaurants")
public class Restaurant implements IRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "website")
    private String website;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<Staff> staffList;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "background_url")
    private String backgroundUrl;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tables> tables;

    @ManyToOne
    @JoinColumn(name = "moderator_id")
    private Moderator moderator;

    @OneToOne(mappedBy = "restaurant")
    private MenuSection menuSection;

    public Restaurant() {
        this.isActive = true;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getWebsite() {
        return website;
    }

    @Override
    public void setWebsite(String website) {
        this.website = website;
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
    public String getLogoUrl() {
        return logoUrl;
    }

    @Override
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String background_url) {
        this.backgroundUrl = background_url;
    }

    @Override
    public Set<Staff> getStaffList() {
        return staffList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setStaffList(Set<Staff> staffList) {
        this.staffList = staffList;
    }

    @Override
    public Set<Tables> getTables() {
        return tables;
    }

    @Override
    public void setTables(Set<Tables> tables) {
        this.tables = tables;
    }

    @Override
    public Moderator getModerator() {
        return moderator;
    }

    @Override
    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
    }

    public MenuSection getMenuSection() {
        return menuSection;
    }

    public void setMenuSection(MenuSection menuSection) {
        this.menuSection = menuSection;
    }
}
