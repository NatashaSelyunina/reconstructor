package reConstructor.domain.Utillity.dto;

import jakarta.validation.constraints.Email;

public class EmailDto {

    @Email(message = "Please provide a valid e-mail address")
    private String email;

    public EmailDto() {
    }

    public EmailDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
