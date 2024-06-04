package reConstructor.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reConstructor.domain.Utillity.dto.PasswordDto;
import reConstructor.domain.dto.StaffDto;
import reConstructor.services.StaffService;

import java.util.List;

@CrossOrigin(origins = "https://reconstructor.me")
@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService service;

    public StaffController(StaffService staffService) {
        this.service = staffService;
    }

    @PostMapping("/{restaurant-id}")
    public ResponseEntity<StaffDto> save(@Valid @RequestBody StaffDto staffDto,
                                         @PathVariable("restaurant-id") long restaurantId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(staffDto, restaurantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDto> findById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @GetMapping("/all/{restaurant-id}")
    public ResponseEntity<List<StaffDto>> findAll(@PathVariable("restaurant-id") long restaurantId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findAll(restaurantId));
    }

    @PutMapping
    public ResponseEntity<StaffDto> update(@RequestBody StaffDto staffDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(staffDto));
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<String> changePassword(@Valid @RequestBody PasswordDto passwordDto,
                                                 @PathVariable long id) {
        service.changePassword(id, passwordDto.getOldPassword(), passwordDto.getNewPassword());
        return ResponseEntity.status(HttpStatus.OK)
                .body("Your password successfully changed");
    }

    @PutMapping("/restore-password/{id}")
    public ResponseEntity<StaffDto> restorePassword(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.restorePassword(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("The employee with id " + id + " has been successfully deleted");
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivateById(@PathVariable long id) {
        service.deactivateById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("The employee with id " + id + " has been placed on inactive status");
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<String> activateById(@PathVariable long id) {
        service.activateById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("The employee with id " + id + " has been placed in an active status");
    }
}
