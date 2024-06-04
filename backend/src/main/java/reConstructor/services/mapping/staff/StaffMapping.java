package reConstructor.services.mapping.staff;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import reConstructor.domain.dto.StaffDto;
import reConstructor.domain.entities.Staff;
import reConstructor.services.mapping.restaurant.RestaurantMapping;
import reConstructor.services.mapping.role.RoleMapping;

@Mapper(componentModel = "spring",uses = {RoleMapping.class, RestaurantMapping.class})
public interface StaffMapping {

  StaffDto mapToDto (Staff user);

  @Named("without_password")
  @Mapping(target = "password",ignore = true)
  StaffDto mapToDtoWithoutPassword (Staff user);

  Staff mapToEntity (StaffDto userDto);
}
