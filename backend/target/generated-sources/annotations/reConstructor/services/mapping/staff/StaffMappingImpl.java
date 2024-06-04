package reConstructor.services.mapping.staff;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.StaffDto;
import reConstructor.domain.entities.Staff;
import reConstructor.services.mapping.role.RoleMapping;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:36+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class StaffMappingImpl implements StaffMapping {

    @Autowired
    private RoleMapping roleMapping;

    @Override
    public StaffDto mapToDto(Staff user) {
        if ( user == null ) {
            return null;
        }

        StaffDto staffDto = new StaffDto();

        staffDto.setId( user.getId() );
        staffDto.setCode( user.getCode() );
        staffDto.setName( user.getName() );
        staffDto.setSurname( user.getSurname() );
        staffDto.setDateOfBirth( user.getDateOfBirth() );
        staffDto.setPassword( user.getPassword() );
        staffDto.setWorking( user.isWorking() );
        staffDto.setActive( user.isActive() );
        staffDto.setRole( roleMapping.mapToDto( user.getRole() ) );

        return staffDto;
    }

    @Override
    public StaffDto mapToDtoWithoutPassword(Staff user) {
        if ( user == null ) {
            return null;
        }

        StaffDto staffDto = new StaffDto();

        staffDto.setId( user.getId() );
        staffDto.setCode( user.getCode() );
        staffDto.setName( user.getName() );
        staffDto.setSurname( user.getSurname() );
        staffDto.setDateOfBirth( user.getDateOfBirth() );
        staffDto.setWorking( user.isWorking() );
        staffDto.setActive( user.isActive() );
        staffDto.setRole( roleMapping.mapToDto( user.getRole() ) );

        return staffDto;
    }

    @Override
    public Staff mapToEntity(StaffDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        Staff staff = new Staff();

        staff.setId( userDto.getId() );
        staff.setCode( userDto.getCode() );
        staff.setName( userDto.getName() );
        staff.setSurname( userDto.getSurname() );
        staff.setDateOfBirth( userDto.getDateOfBirth() );
        staff.setPassword( userDto.getPassword() );
        staff.setWorking( userDto.isWorking() );
        staff.setActive( userDto.isActive() );
        staff.setRole( roleMapping.mapToEntity( userDto.getRole() ) );

        return staff;
    }
}
