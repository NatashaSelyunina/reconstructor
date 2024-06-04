package reConstructor.services.mapping.role;

import org.mapstruct.Mapper;
import reConstructor.domain.dto.RoleDto;
import reConstructor.domain.entities.Role;

@Mapper(componentModel = "spring")
public interface RoleMapping {

  RoleDto mapToDto (Role role);

  Role mapToEntity (RoleDto roleDto);
}
