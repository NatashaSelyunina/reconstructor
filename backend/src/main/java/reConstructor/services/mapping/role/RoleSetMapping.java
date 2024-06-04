package reConstructor.services.mapping.role;

import org.mapstruct.Mapper;
import reConstructor.domain.dto.RoleDto;
import reConstructor.domain.entities.Role;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapping.class})
public interface RoleSetMapping {
    List<RoleDto> mapToDto (List<Role> role);

    List<Role> mapToEntity (List<RoleDto> roleDto);
}
