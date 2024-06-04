package reConstructor.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reConstructor.domain.dto.menuDto.DishDto;
import reConstructor.domain.entities.menu.Dish;
import reConstructor.exception_handling.exceptions.common_classes.DatabaseErrorException;
import reConstructor.exception_handling.exceptions.common_classes.NonExistentIdException;
import reConstructor.repositories.DishRepository;
import reConstructor.services.mapping.menu.dish.DishMapping;

@Service
public class DishService {

    private DishRepository repository;
    private DishMapping mapping;

    public DishService(DishRepository repository, DishMapping mapping) {
        this.repository = repository;
        this.mapping = mapping;
    }

    public DishDto findActiveById (int id) {
        Dish dish = findDishById(id);
        return mapping.mapToDto(dish);
    }

    public Dish findDishById(int id) {
        return repository.findActiveById(id).orElseThrow(() -> new NonExistentIdException(
                "Dish with id " + id + " is not found. Please try again.", HttpStatus.BAD_REQUEST));
    }

    public DishDto update(DishDto dishDto) {
        Dish dish = findDishById(dishDto.getId());
        if (dishDto.getName() != null && !dishDto.getName().isBlank()
                && !dishDto.getName().equals(dish.getName())) {
            dish.setName(dishDto.getName());
        }

        if (dishDto.getDescription() != null && !dishDto.getDescription().isBlank()
                && !dishDto.getDescription().equals(dish.getDescription())) {
            dish.setDescription(dishDto.getDescription());
        }

        if (dishDto.getWeight() != null && !dishDto.getWeight().isBlank()
                && !dishDto.getWeight().equals(dish.getWeight())) {
            dish.setWeight(dishDto.getWeight());
        }

        if (dishDto.getImageUrl() != null && !dishDto.getImageUrl().isBlank()
                && !dishDto.getImageUrl().equals(dish.getImageUrl())) {
            dish.setImageUrl(dishDto.getImageUrl());
        }

        if (Math.abs(dishDto.getPrice() - dish.getPrice()) >= 0.01) {
            dish.setPrice(dishDto.getPrice());
        }

        repository.save(dish);
        return mapping.mapToDto(dish);
    }

    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    public void addDishToSection(int id, int sectionId) {
        repository.addDishToSection(id, sectionId);
    }

    public Dish findByName(String name) {
        return repository.findByName(name);
    }

    public void deleteById(int id) {
        Dish dish = findDishById(id);
        try {
            repository.deleteById(dish.getId());
        } catch (Exception e) {
            throw new DatabaseErrorException(
                    "An error occurred while deleting staff member with id " + id + " from the database.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
