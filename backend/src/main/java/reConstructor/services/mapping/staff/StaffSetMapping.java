package reConstructor.services.mapping.staff;

import java.util.HashSet;
import java.util.Set;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import reConstructor.domain.dto.StaffDto;
import reConstructor.domain.entities.Staff;

@Mapper(componentModel = "spring",uses = {StaffMapping.class})
public interface StaffSetMapping {

  Set<StaffDto> mapToDto (Set<Staff> user);

  @IterableMapping(qualifiedByName = "without_password")
  @Mapping(target = "password",ignore = true)
  Set<StaffDto> mapToDtoWithoutPassword (Set<Staff> user);

  Set<Staff> mapToEntity (Set<StaffDto> userDto);
}
