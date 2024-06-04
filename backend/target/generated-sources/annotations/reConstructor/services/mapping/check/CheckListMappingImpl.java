package reConstructor.services.mapping.check;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.CheckDto;
import reConstructor.domain.entities.Check;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:37+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CheckListMappingImpl implements CheckListMapping {

    @Autowired
    private CheckMapping checkMapping;

    @Override
    public List<CheckDto> mapListToDto(List<Check> checks) {
        if ( checks == null ) {
            return null;
        }

        List<CheckDto> list = new ArrayList<CheckDto>( checks.size() );
        for ( Check check : checks ) {
            list.add( checkMapping.mapToDto( check ) );
        }

        return list;
    }

    @Override
    public List<Check> mapListToEntity(List<CheckDto> checkDtos) {
        if ( checkDtos == null ) {
            return null;
        }

        List<Check> list = new ArrayList<Check>( checkDtos.size() );
        for ( CheckDto checkDto : checkDtos ) {
            list.add( checkMapping.mapToEntity( checkDto ) );
        }

        return list;
    }
}
