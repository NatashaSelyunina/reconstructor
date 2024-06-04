package reConstructor.services.mapping.moderator;

import java.util.LinkedHashSet;
import java.util.Set;
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
public class ModeratorSetMappingImpl implements ModeratorSetMapping {

    @Override
    public Set<ModeratorDto> mapSetToDto(Set<Moderator> moderators) {
        if ( moderators == null ) {
            return null;
        }

        Set<ModeratorDto> set = new LinkedHashSet<ModeratorDto>( Math.max( (int) ( moderators.size() / .75f ) + 1, 16 ) );
        for ( Moderator moderator : moderators ) {
            set.add( moderatorToModeratorDto( moderator ) );
        }

        return set;
    }

    @Override
    public Set<Moderator> mapSetToEntity(Set<ModeratorDto> moderatorDtos) {
        if ( moderatorDtos == null ) {
            return null;
        }

        Set<Moderator> set = new LinkedHashSet<Moderator>( Math.max( (int) ( moderatorDtos.size() / .75f ) + 1, 16 ) );
        for ( ModeratorDto moderatorDto : moderatorDtos ) {
            set.add( moderatorDtoToModerator( moderatorDto ) );
        }

        return set;
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

    protected ModeratorDto moderatorToModeratorDto(Moderator moderator) {
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

    protected Role roleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDto.getId() );
        role.setName( roleDto.getName() );

        return role;
    }

    protected Moderator moderatorDtoToModerator(ModeratorDto moderatorDto) {
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
}
