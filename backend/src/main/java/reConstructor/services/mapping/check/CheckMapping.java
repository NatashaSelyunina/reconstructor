package reConstructor.services.mapping.check;

import org.mapstruct.Mapper;
import reConstructor.domain.dto.CheckDto;
import reConstructor.domain.entities.Check;
import reConstructor.services.mapping.menu.dish.DishSetMapping;

@Mapper(componentModel = "spring",uses = {DishSetMapping.class})
public interface CheckMapping {

  CheckDto mapToDto (Check check);

  Check mapToEntity (CheckDto checkDto);
}
