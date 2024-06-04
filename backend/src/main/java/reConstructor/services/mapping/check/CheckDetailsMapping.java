package reConstructor.services.mapping.check;

import org.mapstruct.Mapper;
import reConstructor.domain.dto.CheckDetailsDto;
import reConstructor.domain.entities.CheckDetails;

@Mapper(componentModel = "spring")
public interface CheckDetailsMapping {
    CheckDetailsDto mapToDto(CheckDetails checkDetails);
    CheckDetails mapToEntity(CheckDetailsDto checkDetailsDto);
}
