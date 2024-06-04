package reConstructor.services.mapping.moderator;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import reConstructor.domain.dto.ModeratorDto;
import reConstructor.domain.entities.Moderator;
import reConstructor.services.mapping.restaurant.RestaurantSetMapping;

@Mapper(componentModel = "spring", uses = {RestaurantSetMapping.class})
public interface ModeratorMapping {

  @Named("mapToDto")
  ModeratorDto mapToDto(Moderator moderator);

  @Named("mapToEntity")
  Moderator mapToEntity(ModeratorDto moderatorDto);
}
