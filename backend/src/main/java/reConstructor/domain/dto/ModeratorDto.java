package reConstructor.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

public class ModeratorDto {

    private long id;
    @Email(message = "Please provide a valid e-mail address")
    private String email;
    @Pattern(regexp = "^(?=.*[a-zA-Z0-9])[a-zA-Z0-9\\x{00C0}-\\x{00FF}]{8,16}$",
            message = "The password must contain 8 to 16 characters, " +
                    "including letters, numbers and special characters")
    private String password;
    @NotBlank(message = "First name must not be blank")
    private String name;
    @NotBlank(message = "Last name must not be blank")
    private String surname;
    private String dateOfBirth;
    private RoleDto role;
    private boolean isActive;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModeratorDto that = (ModeratorDto) o;
        return id == that.id && isActive == that.isActive && Objects.equals(email, that.email)
                && Objects.equals(password, that.password) && Objects.equals(name,
                that.name) && Objects.equals(surname, that.surname) && Objects.equals(
                dateOfBirth, that.dateOfBirth) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, surname, dateOfBirth, role, isActive);
    }
}
