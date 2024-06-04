package reConstructor.services.mapping.check;

import org.mapstruct.Mapper;
import reConstructor.domain.dto.CheckDetailsDto;
import reConstructor.domain.entities.CheckDetails;

import java.util.List;

@Mapper(componentModel = "spring",uses = {CheckDetailsMapping.class})
public interface CheckDetailsListMapping {
    List<CheckDetailsDto> mapListToDto(List<CheckDetails> checkDetails);
    List<CheckDetails> mapListToEntity(List<CheckDetailsDto> checkDetailsDtos);
}
