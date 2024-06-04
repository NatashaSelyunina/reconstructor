package reConstructor.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reConstructor.domain.dto.StaffDto;
import reConstructor.domain.entities.Restaurant;
import reConstructor.domain.entities.Staff;
import reConstructor.exception_handling.exceptions.common_classes.DatabaseErrorException;
import reConstructor.exception_handling.exceptions.common_classes.EmptyRequestBodyException;
import reConstructor.exception_handling.exceptions.common_classes.NonExistentIdException;
import reConstructor.exception_handling.exceptions.common_classes.PasswordMismatchException;
import reConstructor.exception_handling.exceptions.role.NonExistentRoleException;
import reConstructor.exception_handling.exceptions.staff.NonExistentCodeStaffException;
import reConstructor.exception_handling.exceptions.staff.NotFoundWorkingStaffException;
import reConstructor.repositories.StaffRepository;
import reConstructor.security.PasswordEncoder;
import reConstructor.security.access_check.AccessService;
import reConstructor.services.mapping.staff.StaffMapping;

@Service
public class StaffService {

    private final String PASSWORD_MESSAGE = "Password is hidden";
    private StaffRepository repository;
    private RestaurantService restaurantService;
    private StaffMapping mapping;
    private AutoGenerationService autoGenerationService;
    private AccessService accessService;

    public StaffService(StaffRepository staffRepository, RestaurantService restaurantService,
                        StaffMapping staffMapping, AutoGenerationService autoGenerationService,
                        AccessService accessService) {
        this.repository = staffRepository;
        this.restaurantService = restaurantService;
        this.mapping = staffMapping;
        this.autoGenerationService = autoGenerationService;
        this.accessService = accessService;
    }

    public StaffDto save(StaffDto staffDto, long restaurantId) {
        Restaurant restaurant = restaurantService.findActiveById(restaurantId);
        accessService.checkAuthorization(restaurantId);

        correctRoleId(staffDto);

        if (staffDto.getCode() != null && repository.existsByCode(staffDto.getCode())) {
            throw new NonExistentCodeStaffException(
                    "Staff with code " + staffDto.getCode() + " already exists.", HttpStatus.BAD_REQUEST);
        }

        try {
            Staff staff = mapping.mapToEntity(staffDto);
            String password = PasswordEncoder.encode(staffDto.getPassword());
            staff.setPassword(password);
            staff.setActive(true);
            staff.setWorking(true);
            String code = autoGenerationService.generateStaffCode(staff.getRole().getName(),
                    Integer.parseInt(restaurant.getCode()));
            staff.setCode(code);
            staff.setId(0);
            staff.setRestaurant(restaurant);
            staff = repository.save(staff);
            staff.setPassword(PASSWORD_MESSAGE);
            return mapping.mapToDto(staff);
        } catch (Exception e) {
            throw new DatabaseErrorException(
                    "The database is currently unavailable. Please try to make a request later. " +
                            "If the problem reoccurs, contact customer support.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public StaffDto findById(long id) {
        Staff staff = findStaffById(id);
        accessService.checkAuthorization(staff.getRestaurant().getId());

        if (!staff.isWorking()) {
            throw new NotFoundWorkingStaffException("Staff with id " + id + " is not working.",
                    HttpStatus.NOT_FOUND);
        }
        staff.setPassword(PASSWORD_MESSAGE);
        return mapping.mapToDto(staff);
    }

    public List<StaffDto> findAll(long restaurantId) {
        accessService.checkAuthorization(restaurantId);

        try {
            return repository.findAllByRestaurantId(restaurantId)
                    .stream()
                    .map(staff -> {
                        StaffDto staffDto = mapping.mapToDto(staff);
                        staffDto.setPassword(PASSWORD_MESSAGE);
                        return staffDto;
                    })
                    .toList();
        } catch (Exception e) {
            throw new DatabaseErrorException(
                    "An error occurred while retrieving all staff members from the database.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public StaffDto update(StaffDto staffDto) {
        Staff staff = findStaffById(staffDto.getId());
        accessService.checkAuthorization(staff.getRestaurant().getId());
        correctRoleId(staffDto);

        try {
            if (staffDto.getDateOfBirth() != null && !staffDto.getDateOfBirth().isBlank()
                    && !staffDto.getDateOfBirth().equals(staff.getDateOfBirth())) {
                staff.setDateOfBirth(staffDto.getDateOfBirth());
            }

            if (staffDto.getName() != null && !staffDto.getName().isBlank()
                    && !staffDto.getName().equals(staff.getName())) {
                staff.setName(staffDto.getName());
            }

            if (staffDto.getSurname() != null && !staffDto.getSurname().isBlank()
                    && !staffDto.getSurname().equals(staff.getSurname())) {
                staff.setSurname(staffDto.getSurname());
            }

            if (staffDto.getRole() != null && !staffDto.getRole().getName().equals(staff.getRole().getName())) {
                staff.setRole(mapping.mapToEntity(staffDto).getRole());
                staff.setCode(autoGenerationService.generateStaffCode(staff.getRole().getName(),
                        Integer.parseInt(staff.getRestaurant().getCode())));
            }

            staff = repository.save(staff);
            staff.setPassword(PASSWORD_MESSAGE);
            return mapping.mapToDto(staff);
        } catch (Exception e) {
            throw new DatabaseErrorException(
                    "An error occurred while updating staff member in the database.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void changePassword(long id, String oldPassword, String newPassword) {
        Staff staff = findStaffById(id);
        accessService.checkAuthorization(staff.getRestaurant().getId());
        accessService.hasAccessToChangeOrRestorePassword(staff.getCode());

        try {
            if (PasswordEncoder.encoder.matches(oldPassword, staff.getPassword())) {
                staff.setPassword(PasswordEncoder.encode(newPassword));
                repository.save(staff);
            } else {
                throw new PasswordMismatchException(
                        "Old password doesn't match to saved password, please, try again",
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new DatabaseErrorException(
                    "An error occurred while deactivating staff member with id " + id + " from the database.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public StaffDto restorePassword(long id) {
        Staff staff = findStaffById(id);
        accessService.checkAuthorization(staff.getRestaurant().getId());
        accessService.hasAccessToChangeOrRestorePassword(staff.getCode());

        String rawPassword = autoGenerationService.generatePassword();
        staff.setPassword(PasswordEncoder.encode(rawPassword));
        repository.save(staff);
        staff.setPassword(rawPassword);
        return mapping.mapToDto(staff);
    }

    public void deleteById(long id) {
        Staff staff = findStaffById(id);
        accessService.checkAuthorization(staff.getRestaurant().getId());
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseErrorException(
                    "An error occurred while deleting staff member with id " + id + " from the database.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void deactivateById(long id) {
        staffActivation(id, false);
    }

    public void activateById(long id) {
        staffActivation(id, true);
    }

    private void staffActivation(long id, boolean active) {
        Staff staff = findStaffById(id);
        accessService.checkAuthorization(staff.getRestaurant().getId());
        try {
            staff.setActive(active);
            repository.save(staff);
        } catch (Exception e) {
            throw new DatabaseErrorException(
                    "An error occurred while deactivating staff member with id " + id + " from the database.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Staff findByCode(String code) {
        return repository.findByCode(code).orElseThrow(() -> new NonExistentCodeStaffException(
                "User not found", HttpStatus.BAD_REQUEST));
    }

    public Staff findStaffById(long id) {
        return repository.findById(id).orElseThrow(() -> new NonExistentIdException(
                "Staff with id " + id + " not found. Please try again", HttpStatus.NOT_FOUND));
    }

    private void correctRoleId(StaffDto staffDto) {
        if (staffDto.getRole().getName().isBlank()) {
            throw new EmptyRequestBodyException("Role can't be empty!", HttpStatus.BAD_REQUEST);
        }
        switch (staffDto.getRole().getName()) {
            case "ROLE_ADMINISTRATOR":
                staffDto.getRole().setId(3);
                break;
            case "ROLE_WAITER":
                staffDto.getRole().setId(5);
                break;
            case "ROLE_CHEF":
                staffDto.getRole().setId(7);
                break;
            case "ROLE_BARTENDER":
                staffDto.getRole().setId(9);
                break;
            default:
                throw new NonExistentRoleException(
                        "Role with name " + staffDto.getRole().getName() + " does not exist.",
                        HttpStatus.BAD_REQUEST);
        }
    }
}
