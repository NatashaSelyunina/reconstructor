package reConstructor.services.mapping.role;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.RoleDto;
import reConstructor.domain.entities.Role;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:37+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class RoleSetMappingImpl implements RoleSetMapping {

    @Autowired
    private RoleMapping roleMapping;

    @Override
    public List<RoleDto> mapToDto(List<Role> role) {
        if ( role == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( role.size() );
        for ( Role role1 : role ) {
            list.add( roleMapping.mapToDto( role1 ) );
        }

        return list;
    }

    @Override
    public List<Role> mapToEntity(List<RoleDto> roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( roleDto.size() );
        for ( RoleDto roleDto1 : roleDto ) {
            list.add( roleMapping.mapToEntity( roleDto1 ) );
        }

        return list;
    }
}
