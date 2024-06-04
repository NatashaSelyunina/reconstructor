package reConstructor.services.mapping.menu.dish;

import org.mapstruct.*;
import reConstructor.domain.dto.menuDto.DishDto;
import reConstructor.domain.entities.menu.Dish;
import reConstructor.services.mapping.JsonNullableMapping;

@Mapper(
        uses = {JsonNullableMapping.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishMapping {

  DishDto mapToDto (Dish dish);

  Dish mapToEntity (DishDto dishDto);

  @Named("mapToEntityWithoutIdAndIsActive")
  Dish mapToEntityWithoutIdAndIsActive(DishDto dishDto);

  @Named("mapToDtoWithoutSeveralFields")
  DishDto mapToDtoWithoutSeveralFields(Dish dish);
}
