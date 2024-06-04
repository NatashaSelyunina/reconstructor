package reConstructor.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reConstructor.domain.dto.menuDto.DishDto;
import reConstructor.services.DishService;

@CrossOrigin(origins = "https://reconstructor.me")
@RestController
@RequestMapping("/api/dish")
public class DishController {
    private DishService service;

    public DishController(DishService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDto> findActiveById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.findActiveById(id));
    }

    @PutMapping
    public ResponseEntity<DishDto> update(@RequestBody DishDto dishDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(dishDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("The dish with id " + id + " has been successfully deleted");
    }
}
