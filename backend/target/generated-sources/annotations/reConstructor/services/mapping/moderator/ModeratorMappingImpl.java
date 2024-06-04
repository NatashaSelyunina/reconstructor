package reConstructor.services.mapping.moderator;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.ModeratorDto;
import reConstructor.domain.dto.RoleDto;
import reConstructor.domain.entities.Moderator;
import reConstructor.domain.entities.Role;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:36+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ModeratorMappingImpl implements ModeratorMapping {

    @Override
    public ModeratorDto mapToDto(Moderator moderator) {
        if ( moderator == null ) {
            return null;
        }

        ModeratorDto moderatorDto = new ModeratorDto();

        moderatorDto.setId( moderator.getId() );
        moderatorDto.setEmail( moderator.getEmail() );
        moderatorDto.setPassword( moderator.getPassword() );
        moderatorDto.setRole( roleToRoleDto( moderator.getRole() ) );
        if ( moderator.getActive() != null ) {
            moderatorDto.setActive( moderator.getActive() );
        }
        moderatorDto.setName( moderator.getName() );
        moderatorDto.setSurname( moderator.getSurname() );
        moderatorDto.setDateOfBirth( moderator.getDateOfBirth() );

        return moderatorDto;
    }

    @Override
    public Moderator mapToEntity(ModeratorDto moderatorDto) {
        if ( moderatorDto == null ) {
            return null;
        }

        Moderator moderator = new Moderator();

        moderator.setId( moderatorDto.getId() );
        moderator.setEmail( moderatorDto.getEmail() );
        moderator.setPassword( moderatorDto.getPassword() );
        moderator.setRole( roleDtoToRole( moderatorDto.getRole() ) );
        moderator.setActive( moderatorDto.isActive() );
        moderator.setName( moderatorDto.getName() );
        moderator.setSurname( moderatorDto.getSurname() );
        moderator.setDateOfBirth( moderatorDto.getDateOfBirth() );

        return moderator;
    }

    protected RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( role.getId() );
        roleDto.setName( role.getName() );

        return roleDto;
    }

    protected Role roleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDto.getId() );
        role.setName( roleDto.getName() );

        return role;
    }
}
