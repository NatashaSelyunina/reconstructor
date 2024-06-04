package reConstructor.domain.Utillity.dto;

import jakarta.validation.constraints.Pattern;

public class PasswordDto {

    @Pattern(regexp = "^(?=.*[a-zA-Z0-9])[a-zA-Z0-9\\x{00C0}-\\x{00FF}]{8,16}$",
            message = "The password must contain 8 to 16 characters, " +
                    "including letters, numbers and special characters")
    private String newPassword;
    private String oldPassword;
    private String validationCode;

    public PasswordDto() {
    }

    public PasswordDto(String newPassword, String oldPassword, String validationCode) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
        this.validationCode = validationCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }
}
