package reConstructor.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reConstructor.domain.dto.menuDto.MenuSectionDto;
import reConstructor.services.MenuService;

import java.util.List;

@CrossOrigin(origins = "https://reconstructor.me")
@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    @PostMapping("/{restaurant-id}")
    public ResponseEntity<List<MenuSectionDto>> addMenu(@PathVariable("restaurant-id") long restaurantId,
                                        @Valid @RequestBody List<MenuSectionDto> menuSections) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.addMenu(restaurantId, menuSections));
    }

    @GetMapping("/{restaurant-id}")
    public ResponseEntity<List<MenuSectionDto>> getMenu(@PathVariable("restaurant-id") long restaurantId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getMenu(restaurantId));
    }
}
