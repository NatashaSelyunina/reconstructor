package reConstructor.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reConstructor.domain.Utillity.dto.EmailDto;
import reConstructor.domain.Utillity.dto.ValidationCodeDto;
import reConstructor.domain.Utillity.dto.PasswordDto;
import reConstructor.domain.dto.ModeratorDto;
import reConstructor.services.ModeratorService;

@CrossOrigin(origins = "https://reconstructor.me")
@RestController
@RequestMapping("/api/moderator")
public class ModeratorController {

    private ModeratorService service;

    public ModeratorController(ModeratorService service) {
        this.service = service;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> save(@Valid @RequestBody ModeratorDto moderatorDto) {
        service.save(moderatorDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("You will receive activation email on " + moderatorDto.getEmail());
    }

    @GetMapping
    public ResponseEntity<ModeratorDto> findActive() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findActive());
    }

    @PutMapping
    public ResponseEntity<ModeratorDto> update(@RequestBody ModeratorDto moderatorDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(moderatorDto));
    }

    @PutMapping("/activate")
    public ResponseEntity<String> activateModerator(@RequestBody ValidationCodeDto validationCode) {
        service.activate(validationCode.getValidationCode());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Successfully done");
    }

    @PutMapping("/email")
    public ResponseEntity<String> changeEmail(@Valid @RequestBody EmailDto emailDto) {
        service.changeEmail(emailDto.getEmail());
        return ResponseEntity.status(HttpStatus.OK)
                .body("We sent activation email on your new email " + emailDto.getEmail());
    }

    @PutMapping("/password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody PasswordDto passwordDto) {
        service.changePassword(passwordDto.getNewPassword(), passwordDto.getOldPassword());
        return ResponseEntity.status(HttpStatus.OK)
                .body("Password successfully changed");
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody EmailDto emailDto) {
        service.resetPassword(emailDto.getEmail());
        return ResponseEntity.status(HttpStatus.OK)
                .body("Email with password restore data was successfully send");
    }

    @PutMapping("/validate-restored-password")
    public ResponseEntity<String> validateRestoredPassword(@Valid @RequestBody PasswordDto password) {
        service.validateResetPassword(password.getValidationCode(), password.getNewPassword());
        return ResponseEntity.status(HttpStatus.OK)
                .body("Your password successfully changed");
    }
}
