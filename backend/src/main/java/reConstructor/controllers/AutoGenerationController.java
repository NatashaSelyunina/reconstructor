package reConstructor.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reConstructor.services.AutoGenerationService;

@CrossOrigin(origins = "https://reconstructor.me")
@RestController
@RequestMapping("/api/generation")
public class AutoGenerationController {
    private AutoGenerationService service;

    public AutoGenerationController(AutoGenerationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<String> generatePassword() {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.generatePassword());
    }
}
