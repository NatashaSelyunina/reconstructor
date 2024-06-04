package reConstructor.services.mapping.role;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.RoleDto;
import reConstructor.domain.entities.Role;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:38+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class RoleMappingImpl implements RoleMapping {

    @Override
    public RoleDto mapToDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( role.getId() );
        roleDto.setName( role.getName() );

        return roleDto;
    }

    @Override
    public Role mapToEntity(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDto.getId() );
        role.setName( roleDto.getName() );

        return role;
    }
}
