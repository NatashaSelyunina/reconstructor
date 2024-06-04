package reConstructor.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reConstructor.domain.dto.TablesDto;
import reConstructor.domain.entities.Restaurant;
import reConstructor.domain.entities.Tables;
import reConstructor.exception_handling.exceptions.common_classes.NonExistentIdException;
import reConstructor.repositories.TablesRepository;
import reConstructor.security.access_check.AccessService;
import reConstructor.services.mapping.tables.TableListMapping;
import reConstructor.services.mapping.tables.TableMapping;

import java.util.Set;

@Service
public class TablesService {

    private TablesRepository repository;
    private TableMapping mapping;
    private TableListMapping listMapping;
    private RestaurantService restaurantService;
    private QrCodeService qrCodeService;
    private AccessService accessService;

    public TablesService(TablesRepository tablesRepository, TableMapping tableMapping,
                         TableListMapping tableListMapping,
                         QrCodeService qrCodeService, RestaurantService restaurantService,
                         AccessService accessService) {
        this.repository = tablesRepository;
        this.mapping = tableMapping;
        this.listMapping = tableListMapping;
        this.qrCodeService = qrCodeService;
        this.restaurantService = restaurantService;
        this.accessService = accessService;
    }

    public TablesDto save(long restaurantId) {
        Restaurant restaurant = restaurantService.findActiveById(restaurantId);
        accessService.checkAuthorization(restaurantId);

        Long number = Long.parseLong(repository.lastNumber(restaurantId).orElse("0")) + 1;

        Tables table = new Tables(0, number.toString());
        table.setRestaurant(restaurant);
        table = generateQrCode(table);
        repository.save(table);
        return mapping.mapToDto(table);
    }

    public Set<TablesDto> findAllActive(long restaurantId) {
        accessService.checkAuthorization(restaurantId);
        return listMapping.mapListToDto(repository.findAllActive(restaurantId));
    }

    public Set<TablesDto> findAllFree(long restaurantId) {
        accessService.checkAuthorization(restaurantId);
        return listMapping.mapListToDto(repository.findAllFree(restaurantId));
    }

    public Tables findActiveById(int id) {
        Tables table = repository.findActiveById(id).orElseThrow(() -> new NonExistentIdException(
                "Table with id " + id + " not found. Please try again.", HttpStatus.BAD_REQUEST));
        accessService.checkAuthorization(table.getRestaurant().getId());
        return table;
    }

    public TablesDto findDtoActiveById(int id) {
        return mapping.mapToDto(findActiveById(id));
    }

    public void deleteById(int id) {
        Tables table = repository.findById(id).orElseThrow(() -> new NonExistentIdException(
                "Table with id " + id + " not found. Please try again.", HttpStatus.BAD_REQUEST));
        accessService.checkAuthorization(table.getRestaurant().getId());
        repository.deleteById(id);
    }

    public void setBusy(int id) {
        Tables table = findActiveById(id);
        table.setFree(false);
        repository.save(table);
    }

    public void setFree(int id) {
        Tables table = findActiveById(id);
        table.setFree(true);
        repository.save(table);
    }

    // TODO возможно будет не нужен
    public Tables findActiveByNumber(String number) {
        Tables table = repository.findActiveByNumber(number).orElseThrow(() -> new NonExistentIdException(
                "Table not found. Please check the entered data and try again.", HttpStatus.BAD_REQUEST));
        accessService.checkAuthorization(table.getRestaurant().getId());
        return table;
    }

    public Tables generateQrCode(Tables entity) {

        entity.setQrCodeImageUrl(qrCodeService.generateQRCodeImage(
                "https://reconstructor.me/menu/" + entity.getRestaurant().getId() + "/" + entity.getNumber()));
        if (entity.getQrCodeImageUrl().isBlank()) {
            throw new RuntimeException(
                    "Ups something vent wrong can't generate qr-code for table with number:  "
                            + entity.getNumber());
        }
        return entity;
    }
}
