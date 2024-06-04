package reConstructor.services.mapping.check;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.CheckDetailsDto;
import reConstructor.domain.entities.CheckDetails;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:36+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CheckDetailsMappingImpl implements CheckDetailsMapping {

    @Override
    public CheckDetailsDto mapToDto(CheckDetails checkDetails) {
        if ( checkDetails == null ) {
            return null;
        }

        CheckDetailsDto checkDetailsDto = new CheckDetailsDto();

        checkDetailsDto.setId( checkDetails.getId() );
        checkDetailsDto.setDishCount( checkDetails.getDishCount() );
        checkDetailsDto.setItemPrice( checkDetails.getItemPrice() );

        return checkDetailsDto;
    }

    @Override
    public CheckDetails mapToEntity(CheckDetailsDto checkDetailsDto) {
        if ( checkDetailsDto == null ) {
            return null;
        }

        CheckDetails checkDetails = new CheckDetails();

        checkDetails.setId( checkDetailsDto.getId() );
        checkDetails.setDishCount( checkDetailsDto.getDishCount() );
        checkDetails.setItemPrice( checkDetailsDto.getItemPrice() );

        return checkDetails;
    }
}
