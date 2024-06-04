package reConstructor.services.mapping.check;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.CheckDetailsDto;
import reConstructor.domain.entities.CheckDetails;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:35+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CheckDetailsListMappingImpl implements CheckDetailsListMapping {

    @Autowired
    private CheckDetailsMapping checkDetailsMapping;

    @Override
    public List<CheckDetailsDto> mapListToDto(List<CheckDetails> checkDetails) {
        if ( checkDetails == null ) {
            return null;
        }

        List<CheckDetailsDto> list = new ArrayList<CheckDetailsDto>( checkDetails.size() );
        for ( CheckDetails checkDetails1 : checkDetails ) {
            list.add( checkDetailsMapping.mapToDto( checkDetails1 ) );
        }

        return list;
    }

    @Override
    public List<CheckDetails> mapListToEntity(List<CheckDetailsDto> checkDetailsDtos) {
        if ( checkDetailsDtos == null ) {
            return null;
        }

        List<CheckDetails> list = new ArrayList<CheckDetails>( checkDetailsDtos.size() );
        for ( CheckDetailsDto checkDetailsDto : checkDetailsDtos ) {
            list.add( checkDetailsMapping.mapToEntity( checkDetailsDto ) );
        }

        return list;
    }
}
