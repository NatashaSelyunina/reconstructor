package reConstructor.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reConstructor.security.jwt.domain.UserInfoDto;
import reConstructor.security.jwt.services.AuthService;

@CrossOrigin(origins = "https://reconstructor.me")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private AuthService service;

    public UserController(AuthService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<UserInfoDto> getUserFromToken() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getUserFromToken());
    }
}
