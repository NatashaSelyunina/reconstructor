package reConstructor.services.mapping.menu.menu_section;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import reConstructor.domain.dto.menuDto.MenuSectionDto;
import reConstructor.domain.entities.menu.MenuSection;
import reConstructor.services.mapping.menu.dish.DishSetMapping;

@Mapper(componentModel = "spring",uses = {MenuSectionMapping.class, DishSetMapping.class})
public interface MenuSectionListMapping {

  List<MenuSectionDto> mapListToDto (List<MenuSection> menuSections);

  List<MenuSection> mapListToEntity (List<MenuSectionDto> menuSectionDtos);

  @Named("mapListToEntityWithoutSeveralFields")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "isActive", ignore = true)
  List<MenuSection> mapListToEntityWithoutSeveralFields(List<MenuSectionDto> menuSectionDtos);

  @Named("mapListToDtoWithoutSeveralFields")
  @Mapping(target = "isActive", ignore = true)
  @Mapping(target = "dishes", ignore = true)
  List<MenuSectionDto> mapListToDtoWithoutSeveralFields(List<MenuSection> menuSections);
}
