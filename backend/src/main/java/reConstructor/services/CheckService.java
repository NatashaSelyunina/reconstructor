package reConstructor.services;

import org.springframework.stereotype.Service;
import reConstructor.domain.dto.CheckDetailsDto;
import reConstructor.domain.dto.CheckDto;
import reConstructor.domain.entities.Check;
import reConstructor.domain.entities.CheckDetails;
import reConstructor.domain.entities.Tables;
import reConstructor.domain.entities.menu.Dish;
import reConstructor.repositories.CheckRepository;
import reConstructor.services.mapping.check.CheckDetailsMapping;
import reConstructor.services.mapping.check.CheckMapping;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class CheckService {
    private CheckRepository repository;
    private CheckDetailsService checkDetailsService;
    private TablesService tablesService;
    private CheckMapping mapping;
    private CheckDetailsMapping checkDetailsMapping;
    private DishService dishService;

    public CheckService(CheckRepository repository, CheckDetailsService checkDetailsService,
                        CheckMapping mapping, CheckDetailsMapping checkDetailsMapping,
                        TablesService tablesService, DishService dishService) {
        this.repository = repository;
        this.checkDetailsService = checkDetailsService;
        this.mapping = mapping;
        this.checkDetailsMapping = checkDetailsMapping;
        this.tablesService = tablesService;
        this.dishService = dishService;
    }

    public CheckDto saveCheckAndDetails(CheckDto checkDto) {
//        Restaurant restaurant = restaurantMapping.mapToEntity(
//                restaurantService.findActiveById(checkDto.getRestaurantId()));
        Tables table = tablesService.findActiveByNumber(checkDto.getTableNumber());
        Check check = mapping.mapToEntity(checkDto);
        check.setRestTable(table);
        check.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        check.setOrderId(UUID.randomUUID());
        List<CheckDetailsDto> checkDetails = checkDto.getCheckDetails();

        if (!checkDetails.isEmpty()) {
            repository.save(check);
            for (CheckDetailsDto detail : checkDetails) {
                CheckDetails checkDetail = checkDetailsMapping.mapToEntity(detail);
                checkDetail.setCheck(check);
                Dish dish = dishService.findByName(detail.getDishDto().getName());
                checkDetail.setDish(dish);
                checkDetailsService.save(checkDetail);
            }
        }

        return null;
    }

    public void deleteCheckById(Long checkId) {
        checkDetailsService.deleteByCheckId(checkId);
        repository.deleteById(checkId);
    }
}
