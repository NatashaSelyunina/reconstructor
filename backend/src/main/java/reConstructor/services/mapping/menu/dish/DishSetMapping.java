package reConstructor.services.mapping.menu.dish;

import java.util.HashSet;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import reConstructor.domain.dto.menuDto.DishDto;
import reConstructor.domain.entities.menu.Dish;

@Mapper(componentModel = "spring",uses = {DishMapping.class})
public interface DishSetMapping {

  Set<DishDto> mapListToDto (Set<Dish> dishes);

  Set<Dish> mapListToEntity (Set<DishDto> dishDtos);

  @Named("mapSetToDtoWithoutSeveralFields")
  @Mapping(target = "isActive", ignore = true)
  @Mapping(target = "menuSections", ignore = true)
  Set<DishDto> mapSetToDtoWithoutSeveralFields(Set<Dish> dishes);

  @Named("mapSetToEntityWithoutIdAndIsActive")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "isActive", ignore = true)
  Set<Dish> mapSetToEntityWithoutIdAndIsActive(Set<DishDto> dishDtos);
}
