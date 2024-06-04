package reConstructor.domain.Utillity.dto;

public class ValidationCodeDto {

    private String validationCode;

    public ValidationCodeDto() {
    }

    public ValidationCodeDto(String validationCode) {
        this.validationCode = validationCode;
    }


    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }
}
