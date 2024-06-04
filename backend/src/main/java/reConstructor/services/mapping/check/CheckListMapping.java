package reConstructor.services.mapping.check;

import java.util.List;
import org.mapstruct.Mapper;
import reConstructor.domain.dto.CheckDto;
import reConstructor.domain.entities.Check;

@Mapper(componentModel = "spring",uses = {CheckMapping.class})
public interface CheckListMapping {

  List<CheckDto> mapListToDto (List<Check> checks);

  List<Check> mapListToEntity (List<CheckDto> checkDtos);
}
