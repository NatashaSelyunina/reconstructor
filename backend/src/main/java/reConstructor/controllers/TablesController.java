package reConstructor.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reConstructor.domain.dto.TablesDto;
import reConstructor.services.TablesService;

import java.util.Set;

@CrossOrigin(origins = "https://reconstructor.me")
@RestController
@RequestMapping("/api/table")
public class TablesController {
    private TablesService service;

    public TablesController(TablesService tablesService) {
        this.service = tablesService;
    }

    @PostMapping("/{restaurant-id}")
    public ResponseEntity<TablesDto> save(@PathVariable("restaurant-id") long restaurantId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(restaurantId));
    }

    @GetMapping("/all/{restaurant-id}")
    public ResponseEntity<Set<TablesDto>> findAllActive(@PathVariable("restaurant-id") long restaurantId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findAllActive(restaurantId));
    }

    @GetMapping("/allFree/{restaurant-id}")
    public ResponseEntity<Set<TablesDto>> findAllFree(@PathVariable("restaurant-id") long restaurantId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findAllFree(restaurantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TablesDto> findActiveById(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findDtoActiveById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("The table with id " + id + " has been successfully deleted");
    }

    @PutMapping("/setBusy/{id}")
    public ResponseEntity<String> setBusy(@PathVariable("id") int id) {
        service.setBusy(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("The table with id " + id + " was successfully booked");
    }

    @PutMapping("/setFree/{id}")
    public ResponseEntity<String> setFree(@PathVariable("id") int id) {
        service.setFree(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("The table with id " + id + " was successfully vacated");
    }
}
