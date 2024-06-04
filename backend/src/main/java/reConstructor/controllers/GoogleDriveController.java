package reConstructor.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reConstructor.services.GoogleDriveService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/image")
public class GoogleDriveController {

    private GoogleDriveService service;

    public GoogleDriveController(GoogleDriveService service) {
        this.service = service;
    }

    @PostMapping("/menu")
    public Object saveMenuImage(@RequestParam("menu") MultipartFile image) {
        return service.saveMenuImage(image);
    }

    @PostMapping("/logo")
    public Object saveRestaurantLogoImage(@RequestParam("logo") MultipartFile image) {
        return service.saveRestaurantLogoImage(image);
    }

    @PostMapping("/background")
    public Object saveRestaurantBackgroundImage(@RequestParam("background") MultipartFile image) {
        return service.saveRestaurantBackgroundImage(image);
    }

    @DeleteMapping
    public Object deleteImage(@RequestParam String url) {
        return service.deleteImage(url);
    }

    @PutMapping
    public Object updateImage(@RequestParam String url, @RequestParam("image") MultipartFile image) {
        return service.updateImage(url, image);
    }
}
