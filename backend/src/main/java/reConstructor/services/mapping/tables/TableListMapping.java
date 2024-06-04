package reConstructor.services.mapping.tables;

import java.util.Set;

import org.mapstruct.Mapper;
import reConstructor.domain.dto.TablesDto;
import reConstructor.domain.entities.Tables;

@Mapper(componentModel = "spring", uses = {TableMapping.class})
public interface TableListMapping {

  Set<TablesDto> mapListToDto (Set<Tables> tables);

  Set<Tables> mapListToEntity (Set<TablesDto> tableDtos);
}
