package reConstructor.services.mapping.check;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.CheckDetailsDto;
import reConstructor.domain.dto.CheckDto;
import reConstructor.domain.entities.Check;
import reConstructor.domain.entities.CheckDetails;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:35+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CheckMappingImpl implements CheckMapping {

    @Override
    public CheckDto mapToDto(Check check) {
        if ( check == null ) {
            return null;
        }

        CheckDto checkDto = new CheckDto();

        checkDto.setId( check.getId() );
        checkDto.setComment( check.getComment() );
        checkDto.setCheckDetails( checkDetailsListToCheckDetailsDtoList( check.getCheckDetails() ) );

        return checkDto;
    }

    @Override
    public Check mapToEntity(CheckDto checkDto) {
        if ( checkDto == null ) {
            return null;
        }

        Check check = new Check();

        check.setId( checkDto.getId() );
        check.setComment( checkDto.getComment() );
        check.setCheckDetails( checkDetailsDtoListToCheckDetailsList( checkDto.getCheckDetails() ) );

        return check;
    }

    protected CheckDetailsDto checkDetailsToCheckDetailsDto(CheckDetails checkDetails) {
        if ( checkDetails == null ) {
            return null;
        }

        CheckDetailsDto checkDetailsDto = new CheckDetailsDto();

        checkDetailsDto.setId( checkDetails.getId() );
        checkDetailsDto.setDishCount( checkDetails.getDishCount() );
        checkDetailsDto.setItemPrice( checkDetails.getItemPrice() );

        return checkDetailsDto;
    }

    protected List<CheckDetailsDto> checkDetailsListToCheckDetailsDtoList(List<CheckDetails> list) {
        if ( list == null ) {
            return null;
        }

        List<CheckDetailsDto> list1 = new ArrayList<CheckDetailsDto>( list.size() );
        for ( CheckDetails checkDetails : list ) {
            list1.add( checkDetailsToCheckDetailsDto( checkDetails ) );
        }

        return list1;
    }

    protected CheckDetails checkDetailsDtoToCheckDetails(CheckDetailsDto checkDetailsDto) {
        if ( checkDetailsDto == null ) {
            return null;
        }

        CheckDetails checkDetails = new CheckDetails();

        checkDetails.setId( checkDetailsDto.getId() );
        checkDetails.setDishCount( checkDetailsDto.getDishCount() );
        checkDetails.setItemPrice( checkDetailsDto.getItemPrice() );

        return checkDetails;
    }

    protected List<CheckDetails> checkDetailsDtoListToCheckDetailsList(List<CheckDetailsDto> list) {
        if ( list == null ) {
            return null;
        }

        List<CheckDetails> list1 = new ArrayList<CheckDetails>( list.size() );
        for ( CheckDetailsDto checkDetailsDto : list ) {
            list1.add( checkDetailsDtoToCheckDetails( checkDetailsDto ) );
        }

        return list1;
    }
}
