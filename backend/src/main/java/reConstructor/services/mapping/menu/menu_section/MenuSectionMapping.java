package reConstructor.services.mapping.menu.menu_section;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import reConstructor.domain.dto.menuDto.MenuSectionDto;
import reConstructor.domain.entities.menu.MenuSection;
import reConstructor.services.mapping.menu.dish.DishSetMapping;

@Mapper(componentModel = "spring",uses = {DishSetMapping.class})
public interface MenuSectionMapping {

  MenuSectionDto mapToDto (MenuSection menuSection);

  MenuSection mapToEntity (MenuSectionDto menuSectionDto);

  @Named("mapDtoToEntityWithoutSeveralFields")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "active", ignore = true)
  MenuSection mapDtoToEntityWithoutSeveralFields(MenuSectionDto menuSectionDto);

  @Named("mapEntityToDtoWithoutSeveralFields")
  @Mapping(target = "active", ignore = true)
  MenuSectionDto mapEntityToDtoWithoutSeveralFields(MenuSection menuSection);
}
