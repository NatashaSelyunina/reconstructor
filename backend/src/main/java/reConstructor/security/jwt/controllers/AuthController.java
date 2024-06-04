package reConstructor.security.jwt.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reConstructor.exception_handling.exceptions.access.AuthenticationException;
import reConstructor.security.jwt.domain.JwtRequest;
import reConstructor.security.jwt.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService authService) {
        this.service = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody JwtRequest authRequest, HttpServletResponse response) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(service.login(authRequest, response));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/token")
//    public ResponseEntity<Object> getNewTokens(HttpServletRequest request, HttpServletResponse response) {
//        return ResponseEntity.status(HttpStatus.ACCEPTED)
//                .body(service.getNewTokens(request, response));
//    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response) {
        service.deleteCookies(response);
    }
}
