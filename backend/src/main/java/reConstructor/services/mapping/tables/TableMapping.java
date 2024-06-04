package reConstructor.services.mapping.tables;

import org.mapstruct.Mapper;
import reConstructor.domain.dto.TablesDto;
import reConstructor.domain.entities.Tables;
import reConstructor.services.mapping.check.CheckListMapping;

@Mapper(componentModel = "spring", uses = {CheckListMapping.class})
public interface TableMapping {

  TablesDto mapToDto(Tables table);

  Tables mapToEntity(TablesDto tableDto);
}
