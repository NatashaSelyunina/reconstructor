package reConstructor.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reConstructor.domain.dto.CheckDto;
import reConstructor.services.CheckService;

@CrossOrigin(origins = "https://reconstructor.me")
@RestController
@RequestMapping("/api/check")
public class CheckController {
    private CheckService service;

    public CheckController(CheckService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CheckDto> saveCheckAndDetails() {
        return null;
    }
}
