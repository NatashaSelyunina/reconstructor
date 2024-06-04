package reConstructor.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class StaffDto {

    private long id;
    private String code;
    @NotBlank(message = "First name must not be blank")
    private String name;
    @NotBlank(message = "Last name must not be blank")
    private String surname;
    private String dateOfBirth;
    @Pattern(regexp = "^(?=.*[a-zA-Z0-9])[a-zA-Z0-9\\x{00C0}-\\x{00FF}]{8,16}$",
            message = "The password must contain 8 to 16 characters, " +
                    "including letters, numbers and special characters")
    private String password;
    private boolean isWorking;
    private boolean isActive;
    @NotNull(message = "There's bound to be a role")
    private RoleDto role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }
}
