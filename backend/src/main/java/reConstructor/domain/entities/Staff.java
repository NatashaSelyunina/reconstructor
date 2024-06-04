package reConstructor.domain.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import reConstructor.domain.interfaces.IStaff;

@Entity
@Table(name = "staff")
public class Staff implements IStaff, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "password")
    private String password;

    @Column(name = "is_working")
    private boolean isWorking;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Staff() {
        this.isActive = true;
    }

    public Staff(long id, String code, String name, String surname, String dateOfBirth, String password) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.isWorking = true;
        this.isActive = true;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return String.valueOf(code);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isWorking() {
        return isWorking;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setWorking(boolean working) {
        isWorking = working;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public Restaurant getRestaurant() {
        return restaurant;
    }

    @Override
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Staff staff = (Staff) o;
        return id == staff.id && code == staff.code && isWorking == staff.isWorking
                && isActive == staff.isActive && Objects.equals(name, staff.name)
                && Objects.equals(surname, staff.surname) && Objects.equals(dateOfBirth,
                staff.dateOfBirth) && Objects.equals(password, staff.password)
                && Objects.equals(role, staff.role) && Objects.equals(restaurant,
                staff.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, surname, dateOfBirth, password, isWorking, isActive, role,
                restaurant);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", password='" + password + '\'' +
                ", isWorking=" + isWorking +
                ", isActive=" + isActive +
                ", role=" + role +
                ", restaurant=" + restaurant +
                '}';
    }
}
