package reConstructor.services.mapping.restaurant;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import reConstructor.domain.dto.RestaurantDto;
import reConstructor.domain.entities.Restaurant;

@Mapper(componentModel = "spring", uses = {RestaurantMapping.class})
public interface RestaurantSetMapping {

  Set<RestaurantDto> mapSetToDto(Set<Restaurant> restaurant);

  @Named("without_code")
  @Mapping(target = "code", ignore = true)
  Set<RestaurantDto> mapListToDtoWithoutCode(Set<Restaurant> restaurant);

  Set<Restaurant> mapSetToEntity(Set<RestaurantDto> restaurantDto);
}
