package reConstructor.services.mapping.restaurant;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import reConstructor.domain.dto.RestaurantDto;
import reConstructor.domain.entities.Restaurant;
import reConstructor.services.mapping.tables.TableListMapping;

@Mapper(componentModel = "spring", uses = {TableListMapping.class})
public interface RestaurantMapping {

  @Named("mapToDto")
  RestaurantDto mapToDto(Restaurant restaurant);

  @Named("mapToDtoWithoutCode")
  @Mapping(target = "code", ignore = true)
  RestaurantDto mapToDtoWithoutCode(Restaurant restaurant);

  @Named("mapToEntity")
  Restaurant mapToEntity(RestaurantDto restaurantDto);
}
