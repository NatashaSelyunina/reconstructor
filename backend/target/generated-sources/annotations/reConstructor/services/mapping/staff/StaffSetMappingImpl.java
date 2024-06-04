package reConstructor.services.mapping.staff;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.StaffDto;
import reConstructor.domain.entities.Staff;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:36+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class StaffSetMappingImpl implements StaffSetMapping {

    @Autowired
    private StaffMapping staffMapping;

    @Override
    public Set<StaffDto> mapToDto(Set<Staff> user) {
        if ( user == null ) {
            return null;
        }

        Set<StaffDto> set = new LinkedHashSet<StaffDto>( Math.max( (int) ( user.size() / .75f ) + 1, 16 ) );
        for ( Staff staff : user ) {
            set.add( staffMapping.mapToDto( staff ) );
        }

        return set;
    }

    @Override
    public Set<StaffDto> mapToDtoWithoutPassword(Set<Staff> user) {
        if ( user == null ) {
            return null;
        }

        Set<StaffDto> set = new LinkedHashSet<StaffDto>( Math.max( (int) ( user.size() / .75f ) + 1, 16 ) );
        for ( Staff staff : user ) {
            set.add( staffMapping.mapToDtoWithoutPassword( staff ) );
        }

        return set;
    }

    @Override
    public Set<Staff> mapToEntity(Set<StaffDto> userDto) {
        if ( userDto == null ) {
            return null;
        }

        Set<Staff> set = new LinkedHashSet<Staff>( Math.max( (int) ( userDto.size() / .75f ) + 1, 16 ) );
        for ( StaffDto staffDto : userDto ) {
            set.add( staffMapping.mapToEntity( staffDto ) );
        }

        return set;
    }
}
