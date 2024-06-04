package reConstructor.services.mapping.menu.menu_section;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.menuDto.MenuSectionDto;
import reConstructor.domain.entities.menu.MenuSection;
import reConstructor.services.mapping.menu.dish.DishSetMapping;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:35+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class MenuSectionMappingImpl implements MenuSectionMapping {

    @Autowired
    private DishSetMapping dishSetMapping;

    @Override
    public MenuSectionDto mapToDto(MenuSection menuSection) {
        if ( menuSection == null ) {
            return null;
        }

        MenuSectionDto menuSectionDto = new MenuSectionDto();

        menuSectionDto.setId( menuSection.getId() );
        menuSectionDto.setName( menuSection.getName() );
        menuSectionDto.setActive( menuSection.isActive() );
        menuSectionDto.setParentSectionId( menuSection.getParentSectionId() );
        menuSectionDto.setDishes( dishSetMapping.mapListToDto( menuSection.getDishes() ) );

        return menuSectionDto;
    }

    @Override
    public MenuSection mapToEntity(MenuSectionDto menuSectionDto) {
        if ( menuSectionDto == null ) {
            return null;
        }

        MenuSection menuSection = new MenuSection();

        menuSection.setId( menuSectionDto.getId() );
        menuSection.setName( menuSectionDto.getName() );
        menuSection.setActive( menuSectionDto.isActive() );
        menuSection.setDishes( dishSetMapping.mapListToEntity( menuSectionDto.getDishes() ) );
        menuSection.setParentSectionId( menuSectionDto.getParentSectionId() );

        return menuSection;
    }

    @Override
    public MenuSection mapDtoToEntityWithoutSeveralFields(MenuSectionDto menuSectionDto) {
        if ( menuSectionDto == null ) {
            return null;
        }

        MenuSection menuSection = new MenuSection();

        menuSection.setName( menuSectionDto.getName() );
        menuSection.setDishes( dishSetMapping.mapListToEntity( menuSectionDto.getDishes() ) );
        menuSection.setParentSectionId( menuSectionDto.getParentSectionId() );

        return menuSection;
    }

    @Override
    public MenuSectionDto mapEntityToDtoWithoutSeveralFields(MenuSection menuSection) {
        if ( menuSection == null ) {
            return null;
        }

        MenuSectionDto menuSectionDto = new MenuSectionDto();

        menuSectionDto.setId( menuSection.getId() );
        menuSectionDto.setName( menuSection.getName() );
        menuSectionDto.setParentSectionId( menuSection.getParentSectionId() );
        menuSectionDto.setDishes( dishSetMapping.mapListToDto( menuSection.getDishes() ) );

        return menuSectionDto;
    }
}
