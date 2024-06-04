package reConstructor.services;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reConstructor.domain.dto.RestaurantDto;
import reConstructor.domain.entities.Moderator;
import reConstructor.domain.entities.Restaurant;
import reConstructor.exception_handling.exceptions.common_classes.DatabaseErrorException;
import reConstructor.exception_handling.exceptions.common_classes.NonExistentIdException;
import reConstructor.repositories.RestaurantRepository;
import reConstructor.security.access_check.AccessService;
import reConstructor.security.jwt.services.AuthService;
import reConstructor.services.mapping.restaurant.RestaurantSetMapping;
import reConstructor.services.mapping.restaurant.RestaurantMapping;

@Service
public class RestaurantService {

    private RestaurantRepository repository;
    private RestaurantMapping mapping;
    private RestaurantSetMapping setMapping;
    private AutoGenerationService generationService;
    private AuthService authService;
    private AccessService accessService;

    public RestaurantService(RestaurantRepository repository, RestaurantMapping mapping,
                             RestaurantSetMapping setMapping, AutoGenerationService generationService,
                             @Lazy AuthService authService, AccessService accessService) {
        this.repository = repository;
        this.mapping = mapping;
        this.setMapping = setMapping;
        this.generationService = generationService;
        this.authService = authService;
        this.accessService = accessService;
    }

    // сохранение рестика ✅
    public RestaurantDto save(RestaurantDto restaurantDto) {
        try {
            Restaurant restaurant = mapping.mapToEntity(restaurantDto);
            restaurant.setId(0);
            restaurant.setActive(true);

            List<String> existingCodes = repository.getCodes();
            boolean isUnique;

            do {
                String code = generationService.generateRestaurantCode();
                restaurant.setCode(code);
                isUnique = !existingCodes.contains(code) && !code.equals("0000");
            } while (!isUnique);

            restaurant.setModerator(authService.getModerator());
            repository.save(restaurant);
            return mapping.mapToDto(restaurant);
        } catch (Exception e) {
            throw new DatabaseErrorException("The database is currently unavailable. Please try to make a request later. " +
                    "If the problem reoccurs, contact customer support.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // нахождение активного рестика по айди ✅
    public RestaurantDto findDtoActiveById(long id) {
        accessService.checkAuthorization(id);
        return mapping.mapToDto(findActiveById(id));
    }

    public Restaurant findActiveById(long id) {
        return repository.findActiveById(id).orElseThrow(() -> new NonExistentIdException(
                "Restaurant with id " + id + " is not found. Please try again.", HttpStatus.BAD_REQUEST));
    }

    // вывод списка всех рестиков ✅
    public List<RestaurantDto> findAll() {
        Moderator moderator = authService.getModerator();
        Set<Restaurant> restaurants = repository.findAllActive(moderator.getId());
        return setMapping.mapSetToDto(restaurants).stream().toList();
    }

    // обновление рестика ✅
    public RestaurantDto update(RestaurantDto restaurantDto) {
        accessService.checkAuthorization(restaurantDto.getId());

        Restaurant restaurant = findActiveById(restaurantDto.getId());
        if (restaurantDto.getPhoneNumber() != null && !restaurantDto.getPhoneNumber().isBlank()
                && !restaurantDto.getPhoneNumber().equals(restaurant.getPhoneNumber())) {
            restaurant.setPhoneNumber(restaurantDto.getPhoneNumber());
        }

        if (restaurantDto.getAddress() != null && !restaurantDto.getAddress().isBlank()
                && !restaurantDto.getAddress().equals(restaurant.getAddress())) {
            restaurant.setAddress(restaurantDto.getAddress());
        }

        if (restaurantDto.getName() != null && !restaurantDto.getName().isBlank()
                && !restaurantDto.getName().equals(restaurant.getName())) {
            restaurant.setName(restaurantDto.getName());
        }

        if (restaurantDto.getWebsite() != null && !restaurantDto.getWebsite().isBlank()
                && !restaurantDto.getWebsite().equals(restaurant.getWebsite())) {
            restaurant.setWebsite(restaurantDto.getWebsite());
        }

        if (restaurantDto.getLogoUrl() != null && !restaurantDto.getLogoUrl().isBlank()
                && !restaurantDto.getLogoUrl().equals(restaurant.getLogoUrl())) {
            restaurant.setLogoUrl(restaurantDto.getLogoUrl());
        }

        if (restaurantDto.getBackgroundUrl() != null && !restaurantDto.getBackgroundUrl().isBlank()
                && !restaurantDto.getBackgroundUrl().equals(restaurant.getBackgroundUrl())) {
            restaurant.setBackgroundUrl(restaurant.getBackgroundUrl());
        }

        repository.save(restaurant);
        return mapping.mapToDto(restaurant);
    }

    public void deleteById(long id) {
        accessService.checkAuthorization(id);
        Restaurant restaurant = findActiveById(id);
        restaurant.setActive(false);
        repository.save(restaurant);
    }

    public void restoreById(long id) {
        accessService.checkAuthorization(id);
        Restaurant restaurant = repository.findById(id).orElseThrow(() -> new NonExistentIdException(
                "Restaurant with id " + id + "not found. Please try again.", HttpStatus.BAD_REQUEST));

        restaurant.setActive(true);
        repository.save(restaurant);
    }
}
