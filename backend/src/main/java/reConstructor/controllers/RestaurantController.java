package reConstructor.controllers;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reConstructor.domain.dto.RestaurantDto;
import reConstructor.services.RestaurantService;

@CrossOrigin(origins = "https://reconstructor.me")
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> save(@Valid @RequestBody RestaurantDto restaurantDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(restaurantDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> findActiveById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findDtoActiveById(id));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findAll());
    }

    @PutMapping
    public ResponseEntity<RestaurantDto> update(@RequestBody RestaurantDto restaurantDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(restaurantDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("The restaurant with id " + id + " has been successfully deleted");
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<String> restoreById(@PathVariable long id) {
        service.restoreById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("The restaurant with id " + id + " has been successfully restored");
    }
}
