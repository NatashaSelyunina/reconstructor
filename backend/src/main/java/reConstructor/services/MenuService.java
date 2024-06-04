package reConstructor.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import reConstructor.domain.dto.menuDto.DishDto;
import reConstructor.domain.dto.menuDto.MenuSectionDto;
import reConstructor.domain.entities.Restaurant;
import reConstructor.domain.entities.menu.Dish;
import reConstructor.domain.entities.menu.MenuSection;
import reConstructor.exception_handling.exceptions.common_classes.DuplicateNameException;
import reConstructor.repositories.*;
import reConstructor.security.access_check.AccessService;
import reConstructor.services.mapping.menu.dish.DishMapping;
import reConstructor.services.mapping.menu.menu_section.MenuSectionMapping;

import java.util.*;
import java.util.List;

@Service
public class MenuService {
    private MenuSectionRepository repository;
    private MenuSectionMapping mapping;
    private DishMapping dishMapping;
    private RestaurantService restaurantService;
    private DishService dishService;
    private AccessService accessService;

    public MenuService(RestaurantService restaurantService, MenuSectionRepository repository,
                       MenuSectionMapping mapping, DishService dishService, AccessService accessService,
                       DishMapping dishMapping) {
        this.repository = repository;
        this.mapping = mapping;
        this.restaurantService = restaurantService;
        this.dishService = dishService;
        this.accessService = accessService;
        this.dishMapping = dishMapping;
    }

    public List<MenuSectionDto> addMenu(long restaurantId, List<MenuSectionDto> sectionDtos) {
        Restaurant restaurant = restaurantService.findActiveById(restaurantId);
        accessService.checkAuthorization(restaurantId);

        List<MenuSectionDto> menu;
        List<Object[]> objects = repository.getMenu(restaurantId);
        if (!objects.isEmpty()) {
            menu = getMenu(restaurantId);
        } else {
            menu = new ArrayList<>();
        }

        int parentId;
        List<MenuSectionDto> menuSubsections = new ArrayList<>();
        Set<DishDto> dishes;
        MenuSectionDto sectionDto = new MenuSectionDto();

        checkUniqueName(sectionDtos);

        if (menu.isEmpty()) {
            MenuSection mainSection = new MenuSection(0, "Main menu", restaurant);
            repository.save(mainSection);
            parentId = mainSection.getId();
        } else {
            parentId = menu.get(0).getParentSectionId();
        }

        for (MenuSectionDto dto : sectionDtos) {
            if (!menu.isEmpty()) {
                if (isUniqueSectionName(menu, dto.getName())) {
                    for (MenuSectionDto menuSectionDto : menu) {
                        if (menuSectionDto.getName().equals(dto.getName())) {
                            sectionDto = menuSectionDto;
                            menuSubsections = menuSectionDto.getSections();
                            break;
                        }
                    }
                    dishes = dto.getDishes();
                    if (!dishes.isEmpty()) {
                        checkAndSaveDishes(objects, mapping.mapToEntity(sectionDto), dishes);
                    }
                    List<MenuSectionDto> subSections = dto.getSections();
                    if (!subSections.isEmpty()) {
                        checkAndSaveSubsection(objects, subSections, menuSubsections, sectionDto.getId());
                    }
                } else {
                    checkAndSaveSectionAndDishes(dto, objects, parentId, menuSubsections);
                }
            } else {
                checkAndSaveSectionAndDishes(dto, objects, parentId, menuSubsections);
            }
        }

        return getMenu(restaurantId);
    }

    private void checkAndSaveSectionAndDishes(MenuSectionDto sectionDto, List<Object[]> objects, int parentId,
                                              List<MenuSectionDto> menuSubsections) {
        Set<DishDto> dishDtos;
        MenuSection saveSection = saveSection(mapping.mapToEntity(sectionDto), parentId);
        dishDtos = sectionDto.getDishes();
        if (dishDtos != null) {
            checkAndSaveDishes(objects, saveSection, dishDtos);
        }

        List<MenuSectionDto> subSections = sectionDto.getSections();
        if (!subSections.isEmpty()) {
            checkAndSaveSubsection(objects, subSections, menuSubsections, saveSection.getId());
        }
    }

    private static void checkUniqueName(List<MenuSectionDto> sectionDtos) {
        Set<String> sectionsName = new HashSet<>();
        for (MenuSectionDto section : sectionDtos) {
            String sectionName = section.getName();
            if (!sectionsName.add(sectionName)) {
                throw new DuplicateNameException("You sent sections with the same names, " +
                        "rename or merge into one section", HttpStatus.BAD_REQUEST);
            }
        }
    }

    private boolean isUniqueSectionName(List<MenuSectionDto> menu, String sectionName) {
        return menu.stream()
                .anyMatch(section -> section.getName().equals(sectionName));
    }

    private void checkAndSaveSubsection(List<Object[]> objects, List<MenuSectionDto> subSections,
                                        List<MenuSectionDto> menuSubsections, int parentSectionId) {
        checkUniqueName(subSections);

        for (MenuSectionDto subSection : subSections) {
            if (menuSubsections.isEmpty()) {
                MenuSection menuSection = getMenuSubSection(objects, parentSectionId, subSection);
                if (!subSection.getSections().isEmpty()) {
                    parentSectionId = menuSection.getId();
                    checkAndSaveSubsection(objects, subSection.getSections(), menuSubsections, parentSectionId);
                }
            } else {
                if (isUniqueSectionName(menuSubsections, subSection.getName())) {
                    MenuSection section = new MenuSection();
                    for (MenuSectionDto menuSectionDto : menuSubsections) {
                        if (menuSectionDto.getName().equals(subSection.getName())) {
                            section = mapping.mapToEntity(menuSectionDto);
                            menuSubsections = menuSectionDto.getSections();
                            break;
                        }
                    }
                    Set<DishDto> dishDtos = subSection.getDishes();
                    if (!dishDtos.isEmpty()) {
                        checkAndSaveDishes(objects, section, dishDtos);
                    }
                    if (!subSection.getSections().isEmpty()) {
                        parentSectionId = section.getId();
                        checkAndSaveSubsection(objects, subSection.getSections(), menuSubsections, parentSectionId);
                    }
                } else {
                    MenuSection menuSection = getMenuSubSection(objects, parentSectionId, subSection);
                    if (!subSection.getSections().isEmpty()) {
                        parentSectionId = menuSection.getId();
                        menuSubsections = new ArrayList<>();
                        checkAndSaveSubsection(objects, subSection.getSections(), menuSubsections, parentSectionId);
                    }
                }
            }
        }
    }

    private MenuSection getMenuSubSection(List<Object[]> objects, int parentSectionId, MenuSectionDto subSection) {
        MenuSection menuSection = saveSection(mapping.mapToEntity(subSection), parentSectionId);
        Set<DishDto> dishes = subSection.getDishes();
        if (!dishes.isEmpty()) {
            checkAndSaveDishes(objects, menuSection, dishes);
        }
        return menuSection;
    }

    private void checkAndSaveDishes(List<Object[]> objects, MenuSection newSection, Set<DishDto> dishDtos) {
        Set<Dish> dishes = new HashSet<>();
        List<Object[]> objectsToSection = new ArrayList<>();
        if (!objects.isEmpty()) {
            for (Object[] object : objects) {
                if (isExistSection(object, newSection.getId())) {
                    objectsToSection.add(object);
                }
            }
        }

        if (objects.isEmpty()) {
            for (DishDto dishDto : dishDtos) {
                Dish dish = dishMapping.mapToEntity(dishDto);
                dish.setId(0);
                dish.setActive(true);
                dishService.save(dish);
                dishes.add(dish);
                dishService.addDishToSection(dish.getId(), newSection.getId());
            }
        } else {
            for (DishDto dishDto : dishDtos) {
                if (!isExistDish(objects, dishDto.getName())) {
                    Dish dish = dishMapping.mapToEntity(dishDto);
                    dish.setId(0);
                    dish.setActive(true);
                    dishService.save(dish);
                    dishService.addDishToSection(dish.getId(), newSection.getId());
                } else {
                    int dishId = 0;
                    for (Object[] obj : objects) {
                        if (obj[4].equals(dishDto.getName())) {
                            dishId = (int) obj[3];
                        }
                    }
                    if (!objectsToSection.isEmpty()) {
                        if (!isExistDishAndSection(objectsToSection, dishDto.getName(), newSection.getId())) {
                            dishService.addDishToSection(dishId, newSection.getId());
                        }
                    }
                }
            }
        }
    }

    public boolean isExistDish(List<Object[]> objects, String dishName) {
        return objects.stream()
                .anyMatch(arr -> dishName.equals(arr[4]));
    }

    public boolean isExistSection(Object[] object, int sectionId) {
        return object[0].equals(sectionId);
    }

    public boolean isExistDishAndSection(List<Object[]> objects, String dishName, int sectionId) {
        return objects.stream()
                .anyMatch(arr -> (arr[0]).equals(sectionId) && arr[4].equals(dishName));
    }

    public MenuSection saveSection(MenuSection mainSection, int parentId) {
        MenuSection menuSection = new MenuSection();
        menuSection.setId(0);
        menuSection.setActive(true);
        menuSection.setParentSectionId(parentId);
        menuSection.setName(mainSection.getName());
        return repository.save(menuSection);
    }

    public List<MenuSectionDto> getMenu(long restaurantId) {
        restaurantService.findActiveById(restaurantId);
        List<Object[]> list = repository.getMenu(restaurantId);

        List<MenuSectionDto> menu = new ArrayList<>();
        List<MenuSectionDto> currentSections = new ArrayList<>();
        Set<DishDto> dishes = new HashSet<>();
        int mainSectionId = 0;
        if (!list.isEmpty()) {
            mainSectionId = (int) list.get(0)[0];
        }
        int currentSectionId = mainSectionId;
        int level = 0;
        MenuSectionDto menuSection = new MenuSectionDto();

        for (int i = 1; i < list.size(); i++) {
            Object[] row = list.get(i);

            int sectionId = (int) row[0];
            int parentSectionId = (int) row[1];
            String sectionName = (String) row[2];

            if (i != list.size() - 1) {
                if (sectionId == currentSectionId) {
                    checkDish(dishes, row);
                } else {
                    dishes = addDishToSection(dishes, menuSection);
                    currentSectionId = sectionId;
                    menuSection = getSection(sectionId, parentSectionId, sectionName);

                    if (parentSectionId == mainSectionId) {
                        currentSections.add(menuSection);
                        checkDish(dishes, row);
                    } else {
                        menu = setSubSections(menu, level, mainSectionId, currentSections);
                        currentSections = new ArrayList<>();
                        currentSections.add(menuSection);
                        level += 1;
                        mainSectionId = parentSectionId;
                        checkDish(dishes, row);
                    }
                }
            } else {
                if (sectionId != currentSectionId) {
                    dishes = addDishToSection(dishes, menuSection);
                    menuSection = getSection(sectionId, parentSectionId, sectionName);
                    if (parentSectionId != mainSectionId) {
                        menu = setSubSections(menu, level, mainSectionId, currentSections);
                        currentSections = new ArrayList<>();
                    }
                    currentSections.add(menuSection);
                }
                if (row[3] != null) {
                    getDish(dishes, row);
                    menuSection.setDishes(dishes);
                }
                level += 1;
                menu = setSubSections(menu, level, parentSectionId, currentSections);
            }
        }

        return menu;
    }

    private static Set<DishDto> addDishToSection(Set<DishDto> dishes, MenuSectionDto menuSection) {
        if (!dishes.isEmpty()) {
            menuSection.setDishes(dishes);
            dishes = new HashSet<>();
        }
        return dishes;
    }

    private static MenuSectionDto getSection(int sectionId, int parentSectionId, String sectionName) {
        return new MenuSectionDto(sectionId, parentSectionId, sectionName);
    }

    private void checkDish(Set<DishDto> dishes, Object[] row) {
        if (row[3] != null) {
            getDish(dishes, row);
        }
    }

    private void getDish(Set<DishDto> dishes, Object[] row) {
        DishDto dish = new DishDto((int) row[3], (String) row[4], (String) row[5], (String) row[6],
                (double) row[7], (String) row[8]);
        dishes.add(dish);
    }

    public List<MenuSectionDto> setSubSections(List<MenuSectionDto> menu, int level, int parentId,
                                               List<MenuSectionDto> currentSections) {

        switch (level) {
            case 0:
                menu = new ArrayList<>(currentSections);
                break;
            case 1:
                for (MenuSectionDto section : menu) {
                    if (checkForAddSubsections(parentId, currentSections, section)) break;
                }
                break;
            case 2:
                for (MenuSectionDto section : menu) {
                    if (checkForAddSubsections(parentId, currentSections, section)) break;
                    List<MenuSectionDto> subSections2 = section.getSections();
                    for (MenuSectionDto section2 : subSections2) {
                        if (checkForAddSubsections(parentId, currentSections, section2)) break;
                    }
                }
                break;
            case 3:
                checkForAddSubsectionsLevel3(menu, parentId, currentSections);
                break;
            case 4:
                for (MenuSectionDto section : menu) {
                    if (checkForAddSubsections(parentId, currentSections, section)) break;
                    List<MenuSectionDto> subSections2 = section.getSections();
                    checkForAddSubsectionsLevel3(subSections2, parentId, currentSections);
                }
                break;
            case 5:
                for (MenuSectionDto section : menu) {
                    if (checkForAddSubsections(parentId, currentSections, section)) break;
                    List<MenuSectionDto> subSections2 = section.getSections();
                    for (MenuSectionDto section2 : subSections2) {
                        if (checkForAddSubsections(parentId, currentSections, section2)) break;
                        List<MenuSectionDto> subSections3 = section2.getSections();
                        for (MenuSectionDto section3 : subSections3) {
                            if (checkForAddSubsections(parentId, currentSections, section3)) break;
                            List<MenuSectionDto> subSections4 = section3.getSections();
                            for (MenuSectionDto section4 : subSections4) {
                                if (checkForAddSubsections(parentId, currentSections, section4)) break;
                                List<MenuSectionDto> subSections5 = section4.getSections();
                                for (MenuSectionDto section5 : subSections5) {
                                    if (section5.getId() == parentId) {
                                        section5.setSections(currentSections);
                                    }
                                }
                            }
                        }
                    }
                }
                break;
        }
        return menu;
    }

    private void checkForAddSubsectionsLevel3(List<MenuSectionDto> menu, int parentId,
                                              List<MenuSectionDto> currentSections) {
        for (MenuSectionDto section : menu) {
            if (checkForAddSubsections(parentId, currentSections, section)) break;
            List<MenuSectionDto> subSections2 = section.getSections();
            for (MenuSectionDto section2 : subSections2) {
                if (checkForAddSubsections(parentId, currentSections, section2)) break;
                List<MenuSectionDto> subSections3 = section2.getSections();
                for (MenuSectionDto section3 : subSections3) {
                    if (checkForAddSubsections(parentId, currentSections, section3)) break;
                }
            }
        }
    }

    private static boolean checkForAddSubsections(int parentId, List<MenuSectionDto> currentSections,
                                                  MenuSectionDto section) {
        if (section.getId() == parentId) {
            section.setSections(currentSections);
            return true;
        }
        return false;
    }
}
